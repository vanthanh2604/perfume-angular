import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Output } from 'src/app/model/output/output';
import { OutputInfo } from 'src/app/model/outputInfo/output-info';
import { OutputService } from 'src/app/service/output-service/output.service';

@Component({
  selector: 'app-output-detail',
  templateUrl: './output-detail.component.html',
  styleUrls: ['./output-detail.component.css']
})
export class OutputDetailComponent implements OnInit {
  id:number
  output:Output
  outputInfo: OutputInfo[]
  constructor(private outService: OutputService, private router:Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.outService.getInputById(this.id).subscribe((response:any)=>{
      this.output=response.result
    })
    this.outService.getInputDetail(this.id).subscribe((response:any)=>{
      this.outputInfo=response.result
    })
  }
}
