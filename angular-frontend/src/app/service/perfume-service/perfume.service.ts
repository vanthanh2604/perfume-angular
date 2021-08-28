import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PerfumeDto } from 'src/app/model/dto/perfumeDto';
import { Perfume } from 'src/app/model/perfume/perfume';



@Injectable({
  providedIn: 'root'
})
export class PerfumeService {
  baseUrl='http://localhost:8080/api/';

  constructor( private http: HttpClient) { }

  getPerfumes(): Observable<Perfume[]>{
    return this.http.get<Perfume[]>(this.baseUrl+'perfumes');
  }
  getPerfumesById(id: number): Observable<Perfume>{
    return this.http.get<Perfume>(this.baseUrl+'perfume/'+id);
  }
  getBrand(){
    return this.http.get(this.baseUrl+'brand');
  }

  // getAllPerfume(index: number): Observable<Perfume[]> {
  //   return this.http.get<Perfume[]>(this.baseUrl + 'perfumes/page=' + index)
  // }

  search(stringSearch: string): Observable<Perfume[]> {
    return this.http.get<Perfume[]>(this.baseUrl + 'perfumes/search?stringSearch=' + stringSearch)
  }

  deletePerfume(id: number){
    return this.http.delete(this.baseUrl+'perfume/'+id);
  }

  createPerfume(perfume: PerfumeDto):Observable<Perfume>{
    return this.http.post<Perfume>(this.baseUrl+'perfume',perfume);
  }
  
  editPerfume(id: number,perfume: PerfumeDto):Observable<Perfume>{
    return this.http.put<Perfume>(this.baseUrl+'perfume/'+id,perfume);
  }

  getPerfumesByCode(code: String): Observable<Perfume>{
    return this.http.get<Perfume>(this.baseUrl+'perfume-code/'+code);
  }
}
