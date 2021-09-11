import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { InputDto } from 'src/app/model/inputDto/input-dto';
import { Perfume } from 'src/app/model/perfume/perfume';
import { Suplier } from 'src/app/model/suplier/suplier';
import { InputService } from 'src/app/service/input-service/input.service';
import { PerfumeService } from 'src/app/service/perfume-service/perfume.service';
import { ToastService } from 'src/app/service/toast-service/toast-service';
import { ValidateService } from '../validate-message/validate.service';
import { Message } from 'src/app/service/message/message.service';

@Component({
  selector: 'app-input-create',
  templateUrl: './input-create.component.html',
  styleUrls: ['./input-create.component.css']
})
export class InputCreateComponent implements OnInit {
  supliers: Suplier[]
  suplierId: number

  item: InputDto
  perfumes: Perfume[]
  list: Array<InputDto> = []
  form: any
  total = 0;
  constructor(
    private perfumeService: PerfumeService,
    private toastService: ToastService,
    private inputService: InputService,
    private router: Router,
    private fb: FormBuilder) {
    this.form = this.fb.group({
      id: ['', [Validators.required]],
      amount: ['', [Validators.required, ValidateService.numberValidator]],
      price: ['', [Validators.required, ValidateService.numberValidator]],
    });
  }


  ngOnInit(): void {


    this.inputService.getAllSuplier().subscribe((response: any) => {
      this.supliers = response.result
      console.log(response.result)
    })
    this.perfumeService.getPerfumes().subscribe((response: any) => {
      this.perfumes = response
      console.log(response)
    })
  }

  get f() {
    return this.form.controls;
  }

  onSubmit() {
    if (this.form.valid) {//kiểm tra form
      this.item = this.form.value
      this.perfumeService.getPerfumesByCode(this.item.id).subscribe((response: any) => {
        if (response.status == true) {// kiểm tra có per_code đúng không
          this.item.perfume_name = response.result.perfume_name
          const per = this.list.find(item => item.id == this.item.id)
          if (per) {
            this.toastService.warning(Message.msgExist);
          }
          else {
            this.total = this.total + (this.item.amount * this.item.price)
            this.list.push(this.item)
       
          }
        }else {
          this.toastService.warning(Message.msgIncorrect);
        }
      })
    }
    else { this.validateAllFormFields(this.form); }
  }

  deletePerfume(code: any) {
    this.total = this.total - (code.amount * code.price)
    this.list = this.list.filter(item => item !== code);
  }


  createInput() {
    if (this.suplierId) {
      if (this.list.length != 0) {
        this.inputService.inputCreate(this.suplierId, this.list).subscribe((response: any) => {
          if (response.status == 200) {
            this.toastService.show("success");
            this.router.navigateByUrl("/input");
          } else {
            this.toastService.show("fail");
          }
        })
      } else { this.toastService.warning(Message.msgInputInfo); }
    } else { this.toastService.warning(Message.msgSuplier) }
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
