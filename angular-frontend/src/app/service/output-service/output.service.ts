import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InputDto } from 'src/app/model/inputDto/input-dto';
import { InputInfo } from 'src/app/model/inputInfo/input-info';
import { Output } from 'src/app/model/output/output';
import { OutputInfo } from 'src/app/model/outputInfo/output-info';

@Injectable({
  providedIn: 'root'
})
export class OutputService {
  baseUrl='http://localhost:8080/api/';

  constructor( private http: HttpClient) { }

  getOutputs(): Observable<Output[]>{
    return this.http.get<Output[]>(this.baseUrl+'outputs');
  }

  getInputById(id:number):Observable<Output[]>{
    return this.http.get<Output[]>(this.baseUrl+"output/"+id)
  }

  getInputDetail(id:number):Observable<OutputInfo[]>{
    return this.http.get<OutputInfo[]>(this.baseUrl+"output-details/"+id)
  }

  outputCreate(output: Output):Observable<Output>{
    return this.http.post<Output>(this.baseUrl+'output/',output)
  }

  outputInfoCreate(outputId: number, inputInfo: InputDto[]){
    return this.http.post<any>(this.baseUrl+'outputInfo/'+outputId,inputInfo)
  }
}
