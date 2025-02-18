
import { EmpleadoModel } from "./empleado";

export interface  ResponseReqeust{
  uuid:string;
  statusCode:string;
  message:string;
  info:EmpleadoModel[]
}
