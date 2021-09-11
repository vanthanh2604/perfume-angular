import { Component, Input, OnInit } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Label } from 'ng2-charts';
import { ToastService } from 'src/app/service/toast-service/toast-service';
import { StatisticalService } from 'src/app/statistical-service/statistical.service';

@Component({
  selector: 'app-inventory-statistics',
  templateUrl: './inventory-statistics.component.html',
  styleUrls: ['./inventory-statistics.component.css']
})
export class InventoryStatisticsComponent implements OnInit {
  data: Array<number>[] = []
  nhap=0
  xuat=0
  ton=0
  constructor(private statisticalService:StatisticalService,private toastService:ToastService) { }

  ngOnInit(): void {
    this.getInventoryStatistics();
  }

  getInventoryStatistics(){
    this.statisticalService.getInventoryStatistics().subscribe((response:any)=>{
      if(response.status==200){
        this.data=response.result
        this.pieChartData.push(response.result[0])
        this.pieChartData.push(response.result[1])
        this.pieChartData.push(response.result[0]-response.result[1])
        console.log(this.nhap)
      }
      else {this.toastService.warning("Lỗi!");}
    })
  }
  // Pie
  public pieChartOptions: ChartOptions = {
    responsive: true,
    legend: {
      position: 'top',
    },
    plugins: {
      datalabels: {
        formatter: (value:any, ctx:any) => {
          const label = ctx.chart.data.labels[ctx.dataIndex];
          return label;
        },
      },
    }
    
  };
 
  public pieChartLabels: Label[] = ['Nhập kho', 'Xuất kho', 'Tồn kho'];
  public pieChartData: number[]=[];
  public pieChartType: ChartType = 'pie';
  public pieChartColors = [
    {
      backgroundColor: ['rgba(255,0,0,0.3)', 'rgba(0,255,0,0.3)', 'rgba(0,0,255,0.3)'],
    },
  ];
  // events
  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }
}
