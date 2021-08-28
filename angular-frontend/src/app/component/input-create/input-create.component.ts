import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { InputDto } from 'src/app/model/inputDto/input-dto';
import { InputInfoDto } from 'src/app/model/inputInfoDto/input-info-dto';
import { Perfume } from 'src/app/model/perfume/perfume';
import { Suplier } from 'src/app/model/suplier/suplier';
import { InputService } from 'src/app/service/input-service/input.service';
import { PerfumeService } from 'src/app/service/perfume-service/perfume.service';
import { ToastService } from 'src/app/service/toast-service/toast-service';

@Component({
  selector: 'app-input-create',
  templateUrl: './input-create.component.html',
  styleUrls: ['./input-create.component.css']
})
export class InputCreateComponent implements OnInit {
  supliers: Suplier[]
  suplierId: number
  perfume: Perfume
  perfumes: Perfume[]
  inputDto: Array<InputDto> = []
  form: FormGroup
  total = 0;
  constructor(
    private perfumeService: PerfumeService,
    private toastService: ToastService,
    private inputService: InputService,
    private fb: FormBuilder) { }
  msg = " không được để trống"
  validation_messages = {
    perfume_code: [
      { type: 'required', message: "Vui lòng chọn mã sản phẩm"},
    ],
    price: [
      { type: 'required', message: "Giá" + this.msg },
      { type: 'min', message: 'Giá phải >0' },
    ],
    amount: [
      { type: 'required', message: "Số lượng" + this.msg },
      { type: 'min', message: 'Số lượng phải >0' },
    ]
  }


  ngOnInit(): void {
    this.form = this.fb.group({
      perfume_code: [null, [Validators.required]],
      amount: [null, [Validators.required]],
      price: [null, [Validators.required]],
    });

    this.inputService.getAllSuplier().subscribe((response: any) => {
      this.supliers = response.result
      console.log(response.result)
    })
    this.perfumeService.getPerfumes().subscribe((response: any)=>{
      this.perfumes=response
      console.log(response)
    })
  }
  onSubmit() {
    if (this.form.valid) {//kiểm tra form
      this.perfume = this.form.value
      this.perfumeService.getPerfumesByCode(this.perfume.perfume_code).subscribe((response: any) => {
        if (response.status == true) {// kiểm tra có per_code đúng không
          this.perfume.perfume_name=response.result.perfume_name
          const per = this.inputDto.find(item => item.perfume_code == this.perfume.perfume_code)
          if (per) {
            alert("Đã thêm sản phẩm này rồi")
          }
          else {
            this.total = this.total + (this.perfume.amount * this.perfume.price)
            this.inputDto.push(this.perfume)
          }
        }
        else {
          this.toastService.show("Mã sản phẩm không đúng!", { classname: 'bg-danger text-light', delay: 4500 });

        }
      })
    }
    else { this.validateAllFormFields(this.form); }
  }

  deletePerfume(code: any) {
    this.total = this.total - (code.amount * code.price)
    console.log("thông tin ", code);
    this.inputDto = this.inputDto.filter(item => item !== code);
  }


  createInput() {
    if (this.suplierId) {
      if (this.inputDto.length != 0) {
        this.inputService.inputCreate(this.suplierId, this.inputDto).subscribe((response: any) => {
          if (response.status == 200) {
            this.toastService.show("Thành công!", { classname: 'bg-success text-light', delay: 4500 });
            this.ngOnInit();
            this.inputDto=[]
            this.total=0
          } else {
            this.toastService.show("Không thành công!", { classname: 'bg-danger text-light', delay: 5000 });
          }
        })
      }else { this.toastService.show("Không thể tạo phiếu nhập không có sản phẩm nào!", { classname: 'bg-danger text-light', delay: 5000 }); }
    } else { this.toastService.show("Chưa chọn nhà cung cấp!", { classname: 'bg-danger text-light', delay: 5000 }) }
  }



  FILTER = /[^0-9]/g;
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
