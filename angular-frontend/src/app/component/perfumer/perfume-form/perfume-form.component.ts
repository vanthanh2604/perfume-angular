import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Brand } from 'src/app/model/brand/brand';
import { PerfumeDto } from 'src/app/model/dto/perfumeDto';
import { Origin } from 'src/app/model/origin/origin';
import { Perfume } from 'src/app/model/perfume/perfume';
import { PerfumeService } from 'src/app/service/perfume-service/perfume.service';
import { ToastService } from 'src/app/service/toast-service/toast-service';
import { ValidateService } from '../../validate-message/validate.service';
@Component({
  selector: 'app-perfume-form',
  templateUrl: './perfume-form.component.html',
  styleUrls: ['./perfume-form.component.css']
})
export class PerfumeFormComponent implements OnInit {
  perfumeDto = new PerfumeDto();
  perfume = new Perfume();
  brands: Brand[];
  origins: Origin[];
  infoform: any;
  constructor(
    public toastService: ToastService,
    private perfumeService: PerfumeService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder) {
    this.infoform = this.fb.group({
      perfume_name: ['', [Validators.required, Validators.maxLength(50)]],
      price: ['', [Validators.required, ValidateService.numberValidator]],
      description: ['', [Validators.required, Validators.maxLength(255)]],
      brandId: ['', [Validators.required]],
      originId: ['', [Validators.required]],
    });
  }
  idPerfume = this.route.snapshot.params['id'];

  ngOnInit(): void {

    this.perfumeService.getBrand().subscribe((response: any) => {
      this.brands = response;
    })
    this.perfumeService.getOrigin().subscribe((response: any) => {
      this.origins = response;
    })

    if (this.idPerfume != undefined) {
      this.perfumeService.getPerfumesById(this.route.snapshot.params['id']).subscribe((response: any) => {
        if (response.status == true) {
          const p = response.result
          this.perfume = p;
          this.perfume.brandId = this.perfume.brand.id
          this.perfume.originId = this.perfume.brand.id
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
      this.perfumeDto.originId = this.perfumeDto.perfume.originId
      if (this.idPerfume == undefined) {
        console.log(this.perfumeDto);
        this.addPerfume();
      } else {
        if (confirm("Bạn có chắc cập nhật sản phẩm này? ")) {
          this.updatePerfume()
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
        this.infoform.reset();
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
  FILTER = /[^0-9]/g;
  formatInput(input: HTMLInputElement) {
    input.value = input.value.replace(this.FILTER, '');
  }

}

