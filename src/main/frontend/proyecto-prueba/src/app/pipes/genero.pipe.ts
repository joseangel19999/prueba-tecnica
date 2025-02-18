import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'genero',
  standalone: true
})
export class GeneroPipe implements PipeTransform {

  transform(value: string): string{
    if(value=="1"){
      return "Hombre"
    }else if(value=="2"){
      return "Mujer"
    }else if(value==null || (value!=null && value.length==0)){
      return "";
    }
    return "Desconocido";
  }

}
