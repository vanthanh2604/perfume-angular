import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Perfume } from 'src/app/model/perfume/perfume';
import { PerfumeService } from '../../../service/perfume-service/perfume.service';
import { Message } from 'src/app/service/message/message.service';
import { ConfirmBoxSevice } from 'src/app/service/confirmBox/confirmBox.service';

@Component({
  selector: 'app-perfume-detail',
  templateUrl: './perfume-detail.component.html',
  styleUrls: ['./perfume-detail.component.css']
})
export class PerfumeDetailComponent implements OnInit {

  id: number
  perfume: Perfume;
  constructor(
    private perfumeService: PerfumeService,
     private route: ActivatedRoute, 
     private router: Router,
     private confirmBoxSevice:ConfirmBoxSevice) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.perfumeService.getPerfumesById(this.id).subscribe((response: any) => {
      if (response.status == true) {
        this.perfume = response.result;
        console.log(response.result);
      }
      else {
        const confirmBox = this.confirmBoxSevice.confirmBoxMsg();
        confirmBox.openConfirmBox$().subscribe(resp => {
          if (resp.Success == true) { 
            this.router.navigate(['/perfumes']);
          }
        })
        
      }
    })
  }
}
