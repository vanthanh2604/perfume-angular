import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { InputDto } from 'src/app/model/inputDto/input-dto';
import { Output } from 'src/app/model/output/output';
import { Perfume } from 'src/app/model/perfume/perfume';
import { InputService } from 'src/app/service/input-service/input.service';
import { OutputService } from 'src/app/service/output-service/output.service';
import { PerfumeService } from 'src/app/service/perfume-service/perfume.service';
import { ToastService } from 'src/app/service/toast-service/toast-service';

@Component({
  selector: 'app-output-create',
  templateUrl: './output-create.component.html',
  styleUrls: ['./output-create.component.css']
})
export class OutputCreateComponent implements OnInit {
  perfume: Perfume
  perfumes: Perfume[]
  inputDto: Array<InputDto> = []
  form: FormGroup
  formCustomer: FormGroup
  total = 0;
  output = new Output();
  selectedLevel: any
  constructor(
    private perfumeService: PerfumeService,
    private toastService: ToastService,
    private outputService: OutputService,
    private fb: FormBuilder
  ) { }
  msg = " không được để trống"
  validation_messages = {
    perfume_code: [
      { type: 'required', message: "Mã" + this.msg },
    ],
    price: [
      { type: 'required', message: "Giá" + this.msg },
      { type: 'min', message: 'Giá phải >0' },
    ],
    amount: [
      { type: 'required', message: "Số lượng" + this.msg },
      { type: 'min', message: 'Số lượng phải >0' },
    ],
    customerName: [
      { type: 'required', message: "Tên khách hàng" + this.msg },
      { type: 'maxlength', message: 'Không vượt quá 25 kí tự' },
    ],
    phone: [
      { type: 'required', message: "Số điện thoại" + this.msg },
      { type: 'pattern', message: "Vui lòng nhập đúng số điện thoại" },
    ],
    address: [
      { type: 'required', message: "Địa chỉ" + this.msg },
      { type: 'maxlength', message: 'Không vượt quá 100 kí tự' },
    ]
  }
  ngOnInit(): void {
    this.form = this.fb.group({
      perfume_code: ['', [Validators.required, Validators.maxLength(12)]],
      amount: [null, [Validators.required]],
      price: ['', [Validators.required]],
    });
    this.formCustomer = this.fb.group({
      customerName: [null, [Validators.required, Validators.maxLength(25)]],
      phone: [null, [Validators.pattern("^((\\+84-?)|0)?[0-9]{9}$")]],
      address: [null, [Validators.maxLength(100)]],
    });
    this.perfumeService.getPerfumes().subscribe((response: any) => {
      this.perfumes = response
    })
  }
  selected() {
    this.perfume = this.form.value;
    this.perfumeService.getPerfumesByCode(this.perfume.perfume_code).subscribe((response: any) => {
      this.perfume.price = response.result.price
      this.perfume.perfume_name = response.result.perfume_name
      this.form.patchValue(this.perfume);
      console.log(this.perfume)
    })



  }

  onSubmit() {
    if (this.form.valid) {//kiểm tra valid form
      this.perfume = this.form.value
      this.perfumeService.getPerfumesByCode(this.perfume.perfume_code).subscribe((response: any) => {
        const per = this.inputDto.find(item => item.perfume_code == this.perfume.perfume_code)
        if (per) {  //Kiểm tra sản phẩm đã được thêm trong vào list chưa
          this.toastService.show("Đã thêm sản phẩm này vào rồi!", { classname: 'bg-danger text-light', delay: 4500 });
        }
        else {
          if (this.perfume.amount < response.result.amount) { // kiểm tra có quá số lượng hay không
            // if (this.perfume.price > response.result.price && confirm("Giá bán hiện tại nhỏ hơn giá nhập! Bạn có muốn tiếp tục? ")) {
            if (this.perfume.price >= response.result.price) {
              this.total = this.total + (this.perfume.amount * this.perfume.price)
              this.inputDto.push(this.perfume)
            }
            else {
              if (confirm("Giá bán hiện tại nhỏ hơn giá nhập! Bạn có muốn tiếp tục? ")) {
                this.total = this.total + (this.perfume.amount * this.perfume.price)
                this.inputDto.push(this.perfume)
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
    this.inputDto = this.inputDto.filter(item => item !== code);
  }

  createOutput() {
    this.output = this.formCustomer.value
    console.log(this.output)
    if (this.formCustomer.valid) {//kiểm tra valid form
      if (this.inputDto) {
        if (this.inputDto.length != 0) {
          this.outputService.outputCreate(this.output).subscribe((response: any) => {
            this.outputService.outputInfoCreate(response.output.id, this.inputDto).subscribe((response: any) => {
              if (response.status == true) {
                this.toastService.show("Thành công!", { classname: 'bg-success text-light', delay: 4500 });
                this.ngOnInit();
                this.inputDto = []
                this.total = 0
              } else {
                this.toastService.show(response.msg, { classname: 'bg-danger text-light', delay: 5000 });
              }
            })
          })
        }
        else { this.toastService.show("Không thể tạo phiếu nhập không có sản phẩm nào!", { classname: 'bg-danger text-light', delay: 5000 }); }
      } else { this.toastService.show("Chưa nhập đủ thông tin khách hàng!", { classname: 'bg-danger text-light', delay: 5000 }) }
    }
    else { this.validateAllFormFields(this.formCustomer); }
  }




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
