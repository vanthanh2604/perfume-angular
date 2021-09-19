import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmBoxInitializer } from '@costlydeveloper/ngx-awesome-popup';
import { ConfirmBoxService } from '@costlydeveloper/ngx-awesome-popup/ngx-awesome-popup/types/confirm-box/core/confirm-box-service';
import { NgbNavChangeEvent } from '@ng-bootstrap/ng-bootstrap';
import { Input } from 'src/app/model/input/input';
import { Output } from 'src/app/model/output/output';
import { InputService } from 'src/app/service/input-service/input.service';
import { OutputService } from 'src/app/service/output-service/output.service';
import { Message } from 'src/app/service/message/message.service';
import { ConfirmBoxSevice } from 'src/app/service/confirmBox/confirmBox.service';

@Component({
  selector: 'app-inputs',
  templateUrl: './inputs.component.html',
  styleUrls: ['./inputs.component.css']
})
export class InputsComponent implements OnInit {
  @ViewChild('input') input: ElementRef;
  inputs: Input[]
  inputss: Input[]
  outputs: Output[]
  outputss: Output[]
  page = 1;
  pageSize = 6;
  collectionSize = 0;
  FILTER = /[^0-9]/g;
  active: number;
  disabled = true;
  tam: number
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private inputService: InputService,
    private outputService: OutputService,
    private confirmBoxSevice: ConfirmBoxSevice,) { }

  ngOnInit(): void {
    this.inputService.getInputs().subscribe((data: Input[]) => {
      this.inputs = data
      this.inputss = data
      this.collectionSize = data.length
    })
    this.outputService.getOutputs().subscribe((data: Output[]) => {
      this.outputs = data
      this.outputss = data
    })

  }
  //=============Tìm kiếm======================

  search(key: string): void {
    if (this.active == 1) {
      this.searchInput(key);
    }
    else { this.searchOutput(key); }
  }
  searchInput(key: string): void {
    const result: Input[] = [];
    key = key.trim();
    for (const inputt of this.inputss) {
      if (inputt.suplier.suplierName.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || inputt.suplier.suplierName.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        result.push(inputt)
      }
    }
    this.inputs = result
    this.collectionSize = result.length
    this.page = 1
    console.log(result)
    if (result.length === 0 || !key) {
      this.collectionSize = result.length
      this.page = 1
    }
  }

  searchOutput(key: string): void {
    const result: Output[] = [];
    key = key.trim();
    for (const output of this.outputss) {
      if (output.customerName.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        result.push(output)
      }
    }
    this.outputs = result
    this.collectionSize = result.length
    this.page = 1
    console.log(result)
    if (result.length === 0 || !key) {
      this.collectionSize = result.length
      this.page = 1
    }
  }
  onNavChange(changeEvent: NgbNavChangeEvent) {
    if (changeEvent.nextId == 1) {
      this.collectionSize = this.inputs.length
    } else {
      this.collectionSize = this.outputs.length
    }
  }
  //===================Phântrang=============================
  selectPage(page: string) {
    this.page = parseInt(page) || 1;
  }
  formatInput(input: HTMLInputElement) {
    input.value = input.value.replace(this.FILTER, '');
  }
  chang(event: any) {
    this.page = 1;
  }
  //==================chi tiết================
  detailInput(id: any) {
    this.inputService.getInputById(id).subscribe((response: any) => {
      console.log(response)
      if (response.status == 200) {
        this.router.navigate(['/input-detail/', id]);

      }
      else {
        const confirmBox = this.confirmBoxSevice.confirmBoxDeleteLoad();
        confirmBox.openConfirmBox$().subscribe(resp => {
          if (resp.Success == true) {
            this.ngOnInit();
          }
        })
      }
    })
  }

  detailOutput(id: any) {
    this.outputService.getInputById(id).subscribe((response: any) => {
      if (response.status == 200) {
        this.router.navigate(['/output-detail', id]);
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
  onKey(event: any) {
    if (event.key === 'Tab') {
      this.input.nativeElement.focus();
    }
  }
}
