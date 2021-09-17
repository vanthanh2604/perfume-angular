import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Label } from 'ng2-charts';
import { StatisticalService } from 'src/app/service/statistical-service/statistical.service';
@Component({
  selector: 'app-statistical',
  templateUrl: './statistical.component.html',
  styleUrls: ['./statistical.component.css']
})
export class StatisticalComponent implements OnInit {
  revenue_By_Perfume: Array<any>[] = []
  revenue_By_Month: Array<any>[] = []
  page = 1;
  pageSize = 5;
  collectionSize = 0;
  constructor(private statisticalService: StatisticalService, private router: Router,) { }

  ngOnInit(): void {
    this.getStatistical()
    this.getStatisticalMonth()
  }
  getStatistical() {
    this.statisticalService.getStatistical().subscribe((response: any) => {
      if (response.status == 200) {
        this.revenue_By_Perfume = response.result
        this.collectionSize = this.revenue_By_Perfume.length
      } else {
        alert("Lỗi")
      }
    })
  }
  getStatisticalMonth() {
    this.statisticalService.getStatisticalMonth().subscribe((response: any) => {
      if (response.status == 200) {
        this.revenue_By_Month = response.result
      } else {
        alert("Lỗi")
      }
      for (let item of this.revenue_By_Month) {
        const moth = 'Tháng ' + item[0]
        this.month.push(moth)
        this.total.push(item[2])
        this.revenue.push(item[3])
      }
    })
  }


  public barChartOptions: ChartOptions = {
    responsive: true,
    // We use these empty structures as placeholders for dynamic theming.
    scales: { xAxes: [{}], yAxes: [{}] },
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'end',
      }
    }
  };
  // Biểu đồ thống kê theo tháng
  month: any[] = []
  total: any = []
  revenue: any[] = []
  public barChartLabels: Label[] = this.month;
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;

  public barChartData: ChartDataSets[] = [

    { data: this.total, label: 'Tiền thu về' },
    { data: this.revenue, label: 'Tiền lợi nhuận' }
  ];
  // events
  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public update(): void {
    this.month = []
    this.total = []
    this.revenue = []
    this.getStatisticalMonth();
  }


  selectPage(page: string) {
    this.page = parseInt(page) || 1;
  }
  FILTER = /[^0-9]/g;
  formatInput(input: HTMLInputElement) {
    input.value = input.value.replace(this.FILTER, '');
  }
  chang(event: any) {
    this.page = 1;
  }
}
