import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ConfirmBoxInitializer } from '@costlydeveloper/ngx-awesome-popup';
import { TooltipConfig } from 'ngx-bootstrap/tooltip';
import { Perfume } from 'src/app/model/perfume/perfume';
import { ConfirmBoxSevice } from 'src/app/service/confirmBox/confirmBox.service';
import { Message } from 'src/app/service/message/message.service';
import { PerfumeService } from 'src/app/service/perfume-service/perfume.service';
import { ToastService } from 'src/app/service/toast-service/toast-service';


function getAlertConfig(): TooltipConfig {
  return Object.assign(new TooltipConfig(), {
    placement: 'left',
    container: 'body',
    delay: 200
  });
}
@Component({
  selector: 'app-perfume-list',
  templateUrl: './perfume-list.component.html',
  styleUrls: ['./perfume-list.component.css'],
  providers: [{ provide: TooltipConfig, useFactory: getAlertConfig }]
})
export class PerfumeListComponent implements OnInit {
  perfumes: Perfume[];
  perfumess: Perfume[];
  searchPerfume: FormGroup;
  page = 1;
  pageSize = 6;
  collectionSize = 0;
  FILTER = /[^0-9]/g;
  constructor(
    private perfumeService: PerfumeService,
    private router: Router,
    public toastService: ToastService,
    public confirmBoxSevice: ConfirmBoxSevice) {

  }

  ngOnInit(): void {
    this.searchPerfume = new FormGroup({
      namePerfume: new FormControl(''),
    });
    this.perfumeService.getPerfumes().subscribe((data: Perfume[]) => {
      this.perfumes = data;
      this.perfumess = data;
      this.collectionSize = data.length;
    })

  }
  //=============SearchFE===========
  searchPerfumes(key: string): void {
    const result: Perfume[] = [];
    key = key.trim();
    for (const perfume of this.perfumess) {
      if (perfume.id.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || perfume.perfume_name.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || perfume.brand.brand_name.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        result.push(perfume)
      }
    }
    this.perfumes = result
    this.collectionSize = result.length
    this.page = 1
    if (result.length === 0 || !key) {
      this.collectionSize = result.length
      this.page = 1
    }
    if (!key) { this.ngOnInit() }
  }
  //============Xóa sản phẩm=========================
  deletePerfume(id: any) {
    const confirmBox = this.confirmBoxSevice.confirmBoxDelete();
    confirmBox.openConfirmBox$().subscribe(resp => {
      if (resp.Success == true) {
        this.perfumeService.deletePerfume(id).subscribe((response: any) => {
          if (response.status == 200) {
            this.toastService.show("success");
            this.perfumes = this.perfumes.filter((p: any) => {
              return id != p.id;
            })
          } else {
            const confirmBox = this.confirmBoxSevice.confirmBoxDeleteLoad();
            confirmBox.openConfirmBox$().subscribe(r => {
              if (r.Success == true) {
                this.ngOnInit();
              }

            })
          }
        })
      }
    })
  }
  //==========Chi tiết=================
  detailPerfume(id: any) {
    this.perfumeService.getPerfumesById(id).subscribe((response: any) => {
      if (response.status == true) {
        this.router.navigate(['/detail', id]);
      } else {
        const confirmBox = this.confirmBoxSevice.confirmBoxDeleteLoad();
        confirmBox.openConfirmBox$().subscribe(resp => {
          if (resp.Success == true) {
            this.ngOnInit();
          }
        })

      }
    })
  }
  //==========Sửa sản phẩm=================
  editPerfume(id: any) {
    this.perfumeService.getPerfumesById(id).subscribe((response: any) => {
      if (response.status == true) {
        this.router.navigate(['/edit', id]);
      } else {
        const confirmBox = this.confirmBoxSevice.confirmBoxDeleteLoad();
        confirmBox.openConfirmBox$().subscribe(resp => {
          if (resp.Success == true) {
            this.ngOnInit();
          }
        })
      }
    })
  }
  //=========Phân trang==============
  selectPage(page: string) {
    this.page = parseInt(page) || 1;
  }
  formatInput(input: HTMLInputElement) {
    input.value = input.value.replace(this.FILTER, '');
  }
  chang(event: any) {
    this.page = 1;
  }
}
