import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PerfumeDto } from 'src/app/model/dto/perfumeDto';
import { Perfume } from 'src/app/model/perfume/perfume';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})

export class PerfumeService {

  constructor( private http: HttpClient) {}

  getPerfumes(): Observable<Perfume[]>{
    return this.http.get<Perfume[]>(environment.baseUrl+'perfumes');
  }
  getPerfumesById(id: number): Observable<Perfume>{
    return this.http.get<Perfume>(environment.baseUrl+'perfume/'+id);
  }
  getBrand(){
    return this.http.get(environment.baseUrl+'brand');
  }
  getOrigin(){
    return this.http.get(environment.baseUrl+'origin');
  }

  // getAllPerfume(index: number): Observable<Perfume[]> {
  //   return this.http.get<Perfume[]>(this.baseUrl + 'perfumes/page=' + index)
  // }


  deletePerfume(id: number){
    return this.http.delete(environment.baseUrl+'perfume/'+id);
  }

  createPerfume(perfume: PerfumeDto):Observable<Perfume>{
    return this.http.post<Perfume>(environment.baseUrl+'perfume',perfume);
  }
  
  editPerfume(id: number,perfume: PerfumeDto):Observable<Perfume>{
    return this.http.put<Perfume>(environment.baseUrl+'perfume/'+id,perfume);
  }

  getPerfumesByCode(code: String): Observable<Perfume>{
    return this.http.get<Perfume>(environment.baseUrl+'perfume-code/'+code);
  }
}
