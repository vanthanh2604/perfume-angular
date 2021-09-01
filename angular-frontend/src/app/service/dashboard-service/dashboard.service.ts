import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor( private http: HttpClient) { }

  getDashboard(): Observable<any>{
    return this.http.get<any>(environment.baseUrl+'dashboard');
  }
}
