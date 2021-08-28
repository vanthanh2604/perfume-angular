import { Component, OnInit } from '@angular/core';
import { DashboardService } from 'src/app/service/dashboard-service/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  revenue:number
  count_perfume:number
  count_output:number
  profit:number
  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    this.dashboardService.getDashboard().subscribe((response: any)=>{
      this.revenue=response.revenue
      this.count_output=response.count_output
      this.count_perfume=response.count_perfume
      this.profit=response.profit
    })
  }

}
