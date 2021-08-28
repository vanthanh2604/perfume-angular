import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Perfume } from 'src/app/model/perfume/perfume';
import { PerfumeService } from '../../../service/perfume-service/perfume.service';

@Component({
  selector: 'app-perfume-detail',
  templateUrl: './perfume-detail.component.html',
  styleUrls: ['./perfume-detail.component.css']
})
export class PerfumeDetailComponent implements OnInit {

  id:number
  perfume: Perfume;
  constructor(private perfumeService: PerfumeService,private route: ActivatedRoute,private router: Router) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.perfumeService.getPerfumesById(this.id).subscribe((response:any)=>
    {
      if(response.status==true){
        this.perfume=response.result;
        console.log(response);
      }
      else{
        alert("Không tìm thấy sản phẩm này!!")
        this.router.navigate(['/perfumes']);
      }
    })

  }

}
