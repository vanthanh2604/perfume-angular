import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StatisticalService {
  baseUrl='http://localhost:8080/api/';

  constructor( private http: HttpClient) { }

  getStatistical(): Observable<any>{
    return this.http.get<any>(this.baseUrl+'statistical');
  }

  getStatisticalMonth(): Observable<any>{
    return this.http.get<any>(this.baseUrl+'statistical/month');
  }

  getInventoryStatistics(): Observable<any>{
    return this.http.get<any>(this.baseUrl+'statistical/inventory');
  }
}
