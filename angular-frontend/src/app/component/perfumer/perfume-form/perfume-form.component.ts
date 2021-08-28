import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Brand } from 'src/app/model/brand/brand';
import { PerfumeDto } from 'src/app/model/dto/perfumeDto';
import { Perfume } from 'src/app/model/perfume/perfume';
import { PerfumeService } from 'src/app/service/perfume-service/perfume.service';
import { ToastService } from 'src/app/service/toast-service/toast-service';
@Component({
  selector: 'app-perfume-form',
  templateUrl: './perfume-form.component.html',
  styleUrls: ['./perfume-form.component.css']
})
export class PerfumeFormComponent implements OnInit {
  perfumeDto = new PerfumeDto();
  perfume = new Perfume();
  brands: Brand[];

  constructor(public toastService: ToastService, private perfumeService: PerfumeService, private route: ActivatedRoute, private router: Router, private fb: FormBuilder) { }
  infoform: FormGroup;
  idPerfume = this.route.snapshot.params['id'];
  msg=" sản phẩm không được để trống"
  validation_messages = {
    perfume_code: [
      { type: 'required', message: "Mã"+this.msg },
      { type: 'maxlength', message: "Mã sản phẩm không vượt quá 12 kí tự" },
    ],
    perfume_name: [
      { type: 'required', message: "Tên"+this.msg },
      { type: 'maxlength', message: 'Không nhập tên không quá 50 kí tự.' },
    ],
    price: [
      { type: 'required', message: "Giá"+this.msg },
      { type: 'min', message: 'Giá phải >0' },
    ],
    amount: [
      { type: 'required', message: "Số lượng"+this.msg },
      { type: 'min', message: 'Số lượng phải >0' },
    ],
    description: [
      { type: 'required', message: "Mô tả"+this.msg },
      { type: 'maxlength', message: 'Không quá 255 kí tự.' },
    ],
    brandId: [
      { type: 'required', message: 'Vui lòng chọn thương hiệu' },
    ],
  }

  ngOnInit(): void {
    this.infoform = this.fb.group({
      perfume_code: [null, [Validators.required, Validators.maxLength(12)]],
      perfume_name: [null, [Validators.required, Validators.maxLength(50)]],
      price: [null, [Validators.required, Validators.min(0)]],
      amount: [null, [Validators.required, Validators.min(0)]],
      description: ['', [Validators.required, Validators.maxLength(255)]],
      brandId: ['', [Validators.required]],
    });
    this.perfumeService.getBrand().subscribe((response: any) => {
      this.brands = response;
    })

    if (this.idPerfume != undefined) {
      this.perfumeService.getPerfumesById(this.route.snapshot.params['id']).subscribe((response: any) => {
        if (response.status == true) {
          const p = response.result
          this.perfume = p;
          this.perfume.brandId = this.perfume.brand.id
          this.infoform.patchValue(p);
        } else {
          alert(response.msg);
          this.router.navigate(['/perfumes']);
        }

      });
    }

  }
  //==============================
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
  onSubmit() {
    if (this.infoform.valid) {
      this.perfumeDto.perfume = this.infoform.value
      this.perfumeDto.brandId = this.perfumeDto.perfume.brandId
      if (this.idPerfume == undefined) {
        console.log(this.perfumeDto);
        this.addPerfume();
      } else {
        if (confirm("Bạn có chắc cập nhật sản phẩm này? ")) {
          this.updatePerfume()
          console.log(this.perfumeDto)
        }
      }
    } else { this.validateAllFormFields(this.infoform); }
  }
  //========Thêm sản phẩm============
  addPerfume() {
    this.perfumeService.createPerfume(this.perfumeDto).subscribe((response: any) => {
      console.log(response);
      if (response.status == true) {
        this.toastService.show(response.msg, { classname: 'bg-success text-light', delay: 4500 });
        this.ngOnInit();
        // this.router.navigate(['/perfumes']);
      } else {
        this.toastService.show(response.msg, { classname: 'bg-danger text-light', delay: 5000 });
      }
    })
  }

  //==========Cập nhật sản phẩm=============
  updatePerfume() {
    this.perfumeService.editPerfume(this.idPerfume, this.perfumeDto).subscribe((response: any) => {
      console.log(response);
      if (response.status == true) {
        this.toastService.show(response.msg, { classname: 'bg-success text-light', delay: 4000 });
      } else
        this.toastService.show(response.msg, { classname: 'bg-danger text-light', delay: 5000 });

      this.router.navigate(['/perfumes']);
    })
  }
  FILTER= /[^0-9]/g;
  formatInput(input: HTMLInputElement) {
    input.value = input.value.replace(this.FILTER, '');
  }

}

