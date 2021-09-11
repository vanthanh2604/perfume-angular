import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Input } from 'src/app/model/input/input';
import { InputInfo } from 'src/app/model/inputInfo/input-info';
import { InputService } from 'src/app/service/input-service/input.service';
import { Message } from 'src/app/service/message/message.service';

@Component({
  selector: 'app-input-detail',
  templateUrl: './input-detail.component.html',
  styleUrls: ['./input-detail.component.css']
})
export class InputDetailComponent implements OnInit {
  input: Input
  inputInfo: InputInfo[];
  id:number
  total:number
  constructor(private inputService: InputService,private route: ActivatedRoute,private router: Router) {}

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id']
    this.inputService.getInputDetail(this.id).subscribe((response:any)=>{
      this.inputInfo=response.result
    })
    this.inputService.getInputById(this.id).subscribe((response: any)=>{
      this.input=response.result
    })
  }
  
}
