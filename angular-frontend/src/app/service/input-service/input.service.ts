import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Input } from 'src/app/model/input/input';
import { InputDto } from 'src/app/model/inputDto/input-dto';
import { InputInfo } from 'src/app/model/inputInfo/input-info';
import { Suplier } from 'src/app/model/suplier/suplier';

@Injectable({
  providedIn: 'root'
})
export class InputService {
  baseUrl='http://localhost:8080/api/';

  constructor( private http: HttpClient) { }

  getInputs(): Observable<Input[]>{
    return this.http.get<Input[]>(this.baseUrl+'inputs');
  }

  getInputById(id:number):Observable<Input[]>{
    return this.http.get<Input[]>(this.baseUrl+"input/"+id)
  }

  getInputDetail(id:number):Observable<InputInfo[]>{
    return this.http.get<InputInfo[]>(this.baseUrl+"input-details/"+id)
  }

  getAllSuplier(): Observable<Suplier[]>{
    return this.http.get<Suplier[]>(this.baseUrl+'supliers');
  }

  inputCreate(idSup:number,inputDto: InputDto[]){
    return this.http.post<any>(this.baseUrl+'input-create/'+idSup,inputDto)
  }
}
