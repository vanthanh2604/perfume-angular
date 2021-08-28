import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StatisticalService } from 'src/app/statistical-service/statistical.service';

@Component({
  selector: 'app-statistical',
  templateUrl: './statistical.component.html',
  styleUrls: ['./statistical.component.css']
})
export class StatisticalComponent implements OnInit {
  revenue_By_Perfume: Array<any>[] = []
  revenue_By_Month: any
  constructor(private statisticalService: StatisticalService, private router: Router,) { }

  ngOnInit(): void {
    this.getStatistical()
    this.getStatisticalMonth()
  }
  getStatistical() {
    this.statisticalService.getStatistical().subscribe((response: any) => {
      if(response.status==200){
        this.revenue_By_Perfume = response.result
      }else{
        alert("Lỗi")
      }
    })
  }
  getStatisticalMonth() {
    this.statisticalService.getStatisticalMonth().subscribe((response: any) => {
      if(response.status==200){
      this.revenue_By_Month = response.result
      }else{
        alert("Lỗi")
      }
      
    })
  }
}
