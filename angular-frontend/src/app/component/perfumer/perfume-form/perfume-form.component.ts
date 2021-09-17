import { Component, Directive, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Brand } from 'src/app/model/brand/brand';
import { PerfumeDto } from 'src/app/model/dto/perfumeDto';
import { Origin } from 'src/app/model/origin/origin';
import { Perfume } from 'src/app/model/perfume/perfume';
import { PerfumeService } from 'src/app/service/perfume-service/perfume.service';
import { ToastService } from 'src/app/service/toast-service/toast-service';
import { Message } from 'src/app/service/message/message.service';
import { ConfirmBoxSevice } from 'src/app/service/confirmBox/confirmBox.service';
import { FormCanDeactivate } from 'src/app/deactivate/form-can-deactivate/form-can-deactivate';

@Component({
  selector: 'app-perfume-form',
  templateUrl: './perfume-form.component.html',
  styleUrls: ['./perfume-form.component.css']
})
export class PerfumeFormComponent extends FormCanDeactivate implements OnInit {
  get form(): FormGroup {
    return this.infoform;
  }
  perfumeDto = new PerfumeDto();
  perfume = new Perfume();
  brands: Brand[];
  origins: Origin[];
  
  @ViewChild('input') input: ElementRef;

  infoform: FormGroup;
  constructor(
    public toastService: ToastService,
    private perfumeService: PerfumeService,
    private route: ActivatedRoute,
    private router: Router,
    private confirmBoxSevice: ConfirmBoxSevice,private elementRef: ElementRef,
    private fb: FormBuilder) {
    super();
    
  }
  idPerfume = this.route.snapshot.params['id'];
  // submit() {
  //   console.log(this.form.submitted);
  // }
  ngOnInit(): void {
    this.infoform = this.fb.group({
      perfume_name: ['', [Validators.required, Validators.maxLength(50)]],
      description: ['', [Validators.required, Validators.maxLength(255)]],
      brandId: ['', [Validators.required]],
      originId: ['', [Validators.required]],
    });
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
          console.log(this.infoform)
        } else {
          alert(response.msg);
          this.router.navigate(['/perfumes']);
        }
      });
    }
  }
  
  onKey(event: any) {
    if (event.key === 'Tab') {
      this.input.nativeElement.focus();
    }
  }
  get f() {
    return this.infoform.controls;
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
        const confirmBox = this.confirmBoxSevice.confirmBoxUpdate();
        confirmBox.openConfirmBox$().subscribe(resp => {
          if (resp.Success == true) {
            this.updatePerfume()
          }
        })
      }
    } else { 
      this.validateAllFormFields(this.infoform); }
  }
  //========Thêm sản phẩm============
  addPerfume() {
    this.perfumeService.createPerfume(this.perfumeDto).subscribe((response: any) => {
      console.log(response);
      if (response.status == 200) {
        this.toastService.show("success");
        this.router.navigate(['/perfumes']);
      }
      if (response.status == false) {
        this.toastService.warning(Message.msgPerfumeExist);
      }
      if (response.status == 500) {
        this.toastService.warning(Message.systemError);
      } 
    })
  }

  //==========Cập nhật sản phẩm=============
  updatePerfume() {
    this.perfumeService.editPerfume(this.idPerfume, this.perfumeDto).subscribe((response: any) => {
      console.log(response);
      if (response.status == 200) {
        this.toastService.show("success");
        this.router.navigate(['/perfumes']);
      }
      if (response.status == 500) {
        this.toastService.warning(Message.systemError);
      }
    })
  }
  FILTER = /[^0-9]/g;
  formatInput(input: HTMLInputElement) {
    input.value = input.value.replace(this.FILTER, '');
  }

}
