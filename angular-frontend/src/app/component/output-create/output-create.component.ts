import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { InputDto } from 'src/app/model/inputDto/input-dto';
import { Output } from 'src/app/model/output/output';
import { Perfume } from 'src/app/model/perfume/perfume';
import { OutputService } from 'src/app/service/output-service/output.service';
import { PerfumeService } from 'src/app/service/perfume-service/perfume.service';
import { ToastService } from 'src/app/service/toast-service/toast-service';
import { ValidateService } from '../validate-message/validate.service';

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
  form: any
  formCustomer: any
  total = 0;
  // output = new Output();
  selectedLevel: any
  constructor(
    private perfumeService: PerfumeService,
    private toastService: ToastService,
    private outputService: OutputService,
    private fb: FormBuilder
  ) {
    this.form = this.fb.group({
      id: ['', [Validators.required, Validators.maxLength(12)]],
      amount: ['', [Validators.required]],
      price: ['', [Validators.required]],
    });
    this.formCustomer = this.fb.group({
      customerName: ['', [Validators.required, Validators.maxLength(25)]],
      phone: ['', [ValidateService.phoneValidator]],
      address: ['', [Validators.maxLength(100)]],
    });
   }
  ngOnInit(): void {
  
    this.perfumeService.getPerfumes().subscribe((response: any) => {
      this.perfumes = response
    })
  }
  selected() {
    this.perfume = this.form.value;
    this.perfumeService.getPerfumesByCode(this.perfume.id).subscribe((response: any) => {
      this.perfume.price = response.result.price
      this.perfume.perfume_name = response.result.perfume_name
      this.form.patchValue(this.perfume);
    })
  }

  onSubmit() {
    if (this.form.valid) {//kiểm tra valid form
      this.perfume = this.form.value
      this.perfumeService.getPerfumesByCode(this.perfume.id).subscribe((response: any) => {
        const per = this.list.find(item => item.id == this.perfume.id)
        if (per) {  //Kiểm tra sản phẩm đã được thêm trong vào details chưa
          this.toastService.show("Đã thêm sản phẩm này vào rồi!", { classname: 'bg-danger text-light', delay: 4500 });
        }
        else {
          if (this.perfume.amount < response.result.amount) { // kiểm tra có quá số lượng hay không
            if (this.perfume.price >= response.result.price) {
              this.total = this.total + (this.perfume.amount * this.perfume.price)
              this.list.push(this.perfume)

            }
            else {
              if (confirm("Giá bán hiện tại nhỏ hơn giá nhập! Bạn có muốn tiếp tục? ")) {
                this.total = this.total + (this.perfume.amount * this.perfume.price)
                this.list.push(this.perfume)
                this.form.reset();
              }
            }
          }
          else {
            this.toastService.show("Nhập quá số lượng tồn trong kho!", { classname: 'bg-danger text-light', delay: 4500 });
          }
        }
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
      if (this.list) {
        if (this.list.length != 0) {
          this.outputService.outputCreate(this.output).subscribe((response: any) => {

            if (response.status == 200) {
              this.toastService.show("Thành công!", { classname: 'bg-success text-light', delay: 4500 });
              this.ngOnInit();
              this.list = []
              this.total = 0
            } else {
              this.toastService.show(response.msg, { classname: 'bg-danger text-light', delay: 5000 });
            }

          })
        }
        else { this.toastService.show("Không thể tạo phiếu nhập không có sản phẩm nào!", { classname: 'bg-danger text-light', delay: 5000 }); }
      } else { this.toastService.show("Chưa nhập đủ thông tin khách hàng!", { classname: 'bg-danger text-light', delay: 5000 }) }
    }
    else { this.validateAllFormFields(this.formCustomer); }

  }
  // createOutput() {
  //   this.output = this.formCustomer.value
  //   console.log(this.output)
  //   if (this.formCustomer.valid) {//kiểm tra valid form
  //     if (this.list) {
  //       if (this.list.length != 0) {
  //         this.outputService.outputCreate(this.output).subscribe((response: any) => {
  //           this.outputService.outputInfoCreate(response.output.id, this.list).subscribe((response: any) => {
  //             if (response.status == 200) {
  //               this.toastService.show("Thành công!", { classname: 'bg-success text-light', delay: 4500 });
  //               this.ngOnInit();
  //               this.list = []
  //               this.total = 0
  //             } else {
  //               this.toastService.show(response.msg, { classname: 'bg-danger text-light', delay: 5000 });
  //             }
  //           })
  //         })
  //       }
  //       else { this.toastService.show("Không thể tạo phiếu nhập không có sản phẩm nào!", { classname: 'bg-danger text-light', delay: 5000 }); }
  //     } else { this.toastService.show("Chưa nhập đủ thông tin khách hàng!", { classname: 'bg-danger text-light', delay: 5000 }) }
  //   }
  //   else { this.validateAllFormFields(this.formCustomer); }
  // }





  FILTER = /[^0-9]/;
  formatInput(input: HTMLInputElement) {
    input.value = input.value.replace(this.FILTER, '');
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
