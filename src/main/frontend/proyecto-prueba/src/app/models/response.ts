
import { EmpleadoModel } from "./empleado";

export interface  ResponseReqeust{
  uuid:string;
  statusCode:number;
  message:string;
  info:EmpleadoModel[]
}
