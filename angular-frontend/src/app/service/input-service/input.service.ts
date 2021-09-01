import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Input } from 'src/app/model/input/input';
import { InputDto } from 'src/app/model/inputDto/input-dto';
import { InputInfo } from 'src/app/model/inputInfo/input-info';
import { Suplier } from 'src/app/model/suplier/suplier';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InputService {

  constructor( private http: HttpClient) { }

  getInputs(): Observable<Input[]>{
    return this.http.get<Input[]>(environment.baseUrl+'inputs');
  }

  getInputById(id:number):Observable<Input[]>{
    return this.http.get<Input[]>(environment.baseUrl+"input/"+id)
  }

  getInputDetail(id:number):Observable<InputInfo[]>{
    return this.http.get<InputInfo[]>(environment.baseUrl+"input-details/"+id)
  }

  getAllSuplier(): Observable<Suplier[]>{
    return this.http.get<Suplier[]>(environment.baseUrl+'supliers');
  }

  inputCreate(idSup:number,inputDto: InputDto[]){
    return this.http.post<any>(environment.baseUrl+'input-create/'+idSup,inputDto)
  }
}
