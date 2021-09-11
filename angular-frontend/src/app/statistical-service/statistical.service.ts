import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class StatisticalService {

  constructor( private http: HttpClient) { }

  getStatistical(): Observable<any>{
    return this.http.get<any>(environment.baseUrl+'statistical');
  }

  getStatisticalMonth(): Observable<any>{
    return this.http.get<any>(environment.baseUrl+'statistical/month');
  }

  getInventoryStatistics(): Observable<any>{
    return this.http.get<any>(environment.baseUrl+'statistical/inventory');
  }
}
