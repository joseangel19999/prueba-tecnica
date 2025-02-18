import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmpleadoModel } from '../models/empleado';
import { Observable } from 'rxjs';
import { ResponseReqeust } from '../models/response';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoServiceService {
  private baseUrlProtocol:string="http://localhost:9085";
  private contextRoot:string= "/prueba-app/api/v1";
  private pathSaveEmpleado: string = "/empleado/save";
  private pathEmpleado: string = "/empleado/";
  private pathGetEmpleadoById: string = "/empleado/search";
  private pathUpdateEmpleado: string = "/empleado/update";
  constructor(private http: HttpClient) {
   }

  saveEmpleado(empleado:EmpleadoModel):Observable<ResponseReqeust>{
    console.log("empleado: "+JSON.stringify(empleado));
    return this.http.post<ResponseReqeust>(this.baseUrlProtocol.concat(this.contextRoot).concat(this.pathSaveEmpleado),empleado);
  }

  getEmpleados():Observable<ResponseReqeust>{
    return this.http.get<ResponseReqeust>(this.baseUrlProtocol.concat(this.contextRoot).concat(this.pathEmpleado));
  }

  getEmpleadopoId(id:string):Observable<ResponseReqeust>{
    const params = new HttpParams().set('id', id);
    return this.http.get<ResponseReqeust>(this.baseUrlProtocol.concat(this.contextRoot).concat(this.pathGetEmpleadoById),{params});
  }
  updateEmpleado(empleado:EmpleadoModel){
    return this.http.post<ResponseReqeust>(this.baseUrlProtocol.concat(this.contextRoot).concat(this.pathUpdateEmpleado),empleado);
  }

  deleteEmpleado(id:string):Observable<ResponseReqeust>{
    const params = new HttpParams().set('id', id);
    return this.http.delete<ResponseReqeust>(this.baseUrlProtocol.concat(this.contextRoot).concat(this.pathEmpleado),{params});
  }
}
