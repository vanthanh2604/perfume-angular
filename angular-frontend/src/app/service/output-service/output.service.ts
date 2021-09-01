import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InputDto } from 'src/app/model/inputDto/input-dto';
import { InputInfo } from 'src/app/model/inputInfo/input-info';
import { Output } from 'src/app/model/output/output';
import { OutputInfo } from 'src/app/model/outputInfo/output-info';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class OutputService {

  constructor( private http: HttpClient) { }

  getOutputs(): Observable<Output[]>{
    return this.http.get<Output[]>(environment.baseUrl+'outputs');
  }

  getInputById(id:number):Observable<Output[]>{
    return this.http.get<Output[]>(environment.baseUrl+"output/"+id)
  }

  getInputDetail(id:number):Observable<OutputInfo[]>{
    return this.http.get<OutputInfo[]>(environment.baseUrl+"output-details/"+id)
  }

  outputCreate(output: Output):Observable<Output>{
    return this.http.post<Output>(environment.baseUrl+'output/',output)
  }

  outputInfoCreate(outputId: number, inputInfo: InputDto[]){
    return this.http.post<any>(environment.baseUrl+'outputInfo/'+outputId,inputInfo)
  }
}
