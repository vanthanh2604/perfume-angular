import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { InputDto } from 'src/app/model/inputDto/input-dto';
import { Output } from 'src/app/model/output/output';
import { Perfume } from 'src/app/model/perfume/perfume';
import { OutputService } from 'src/app/service/output-service/output.service';
import { PerfumeService } from 'src/app/service/perfume-service/perfume.service';
import { ToastService } from 'src/app/service/toast-service/toast-service';
import { ValidateService } from '../validate-message/validate.service';
import { Message } from 'src/app/service/message/message.service';
import { ConfirmBoxSevice } from 'src/app/service/confirmBox/confirmBox.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-output-create',
  templateUrl: './output-create.component.html',
  styleUrls: ['./output-create.component.css']
})
export class OutputCreateComponent implements OnInit {
  perfumes: Perfume[]
  perfume: Perfume
  list: Array<Perfume> = []
  output: Output;
  form: FormGroup
  formCustomer: any
  total = 0;
  amount=0
  @ViewChild('input') input: ElementRef;
  constructor(
    private perfumeService: PerfumeService,
    private router: Router,
    private toastService: ToastService,
    private outputService: OutputService,
    private fb: FormBuilder,
    private confirmBoxSevice: ConfirmBoxSevice
  ) {

  }
  ngOnInit(): void {
    this.form = this.fb.group({
      perfume_name: ["", [Validators.required]],
      amount: ["", [Validators.required, ValidateService.numberValidator]],
      price: ["", [Validators.required, ValidateService.numberValidator]],
    });
    this.formCustomer = this.fb.group({
      customerName: ["", [Validators.required, Validators.maxLength(25)]],
      phone: ["", [ValidateService.phoneValidator]],
      address: ["", [Validators.maxLength(100)]],
    });
    this.perfumeService.getPerfumesStocking().subscribe((response: any) => {
      this.perfumes = response
    })
  }

  onKey(event: any) {
    if (event.key === 'Tab') {
      this.input.nativeElement.focus();
    }
  }

  get fc() {
    return this.formCustomer.controls
  }
  get f() {
    return this.form.controls
  }

  selected(name:any) {
    this.perfumeService.getPerfumesByName(name).subscribe((response: any) => {
      this.amount=response.result.amount
      this.form.patchValue({ price: response.result.price });
    })
  }

  onSubmit() {
    if (this.form.valid) {//kiểm tra valid form
      this.perfume = this.form.value
      this.perfumeService.getPerfumesByName(this.perfume.perfume_name).subscribe((response: any) => {
        if (response.status == 200) {
          this.perfume.id = response.result.id
          const per = this.list.find(item => item.id == this.perfume.id)
          if (per) {  //Kiểm tra sản phẩm đã được thêm trong vào details chưa
            this.toastService.warning(Message.msgExist);
          }
          else {
            if (this.perfume.amount <= response.result.amount) { // kiểm tra có quá số lượng hay không
              if (this.perfume.price >= response.result.price) {
                this.total = this.total + (this.perfume.amount * this.perfume.price)
                this.list.push(this.perfume)
                this.form.patchValue({ amount: "" });
                this.form.patchValue({ price: "" });
                this.form.reset()

              }
              else {
                const confirmBox = this.confirmBoxSevice.confirmBoxOutput();
                confirmBox.openConfirmBox$().subscribe(resp => {
                  if (resp.Success == true) {
                    this.total = this.total + (this.perfume.amount * this.perfume.price)
                    this.list.push(this.perfume)
                    this.form.reset();
                  }
                })
              }
            }
            else {
              this.toastService.warning(Message.msgOverQuantity);
            }
          }
        }else this.toastService.warning(Message.msgChoosingTheWrong);
      })

    }
    else { this.validateAllFormFields(this.form); }
  }

  deletePerfume(code: any) {
    this.total = this.total - (code.amount * code.price)
    this.list = this.list.filter(item => item !== code);
  }

  createOutput() {
    this.output = this.formCustomer.value
    this.output.outputinfo = this.list
    console.log(this.output)
    if (this.formCustomer.valid) {//kiểm tra valid form
      if (this.list.length != 0) {
        this.outputService.outputCreate(this.output).subscribe((response: any) => {
          if (response.status == 200) {
            this.toastService.show("success");
            this.router.navigateByUrl("/input");
          } else {
            this.toastService.show("fail");
          }
        })
      } else { this.toastService.warning(Message.msgInputInfo) }
    }
    else { this.validateAllFormFields(this.formCustomer); }

  }
  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      console.log(field);
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control);
      }
    });
  }
}
