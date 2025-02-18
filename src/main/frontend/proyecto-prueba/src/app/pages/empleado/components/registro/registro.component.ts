import { Component, Inject } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogState } from '@angular/material/dialog';
import { EmpleadoModel } from '../../../../models/empleado';
import { MatSelectModule } from '@angular/material/select';
import { EmpleadoServiceService } from '../../../../services/empleado-service.service';
import { ResponseReqeust } from '../../../../models/response';
import Swal, { SweetAlertIcon } from 'sweetalert2';
import { OptionValueModified } from '../../../../models/options-value-modified';
import { debounceTime, map, Observable, of, switchMap } from 'rxjs';
import { CommonModule } from '@angular/common';
import { ErrorUtils } from '../../../../utils/util.services';

interface Sexo {
  id: string;
  nombre: string;
}
@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatCardModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatProgressBarModule,
    CommonModule],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css'
})
export class RegistroComponent {
  private messageTilteOprationSucces = "Operacion Exitoso";
  private statusResponse = "1";
  private statusOperationUpate = "2";
  sexo: Sexo[] = [
    { id: "1", nombre: 'Hombre' },
    { id: "2", nombre: 'Mujer' }
  ];

  readonly formEmpleado: FormGroup;
  constructor(private _dialogConfirmacion: MatDialogRef<RegistroComponent>,
    @Inject(MAT_DIALOG_DATA) public optionValue: OptionValueModified, private fr: FormBuilder,
    private empleadoService: EmpleadoServiceService) {
     this.formEmpleado = fr.group({
      id: ["",],
      nombre: ["", [Validators.required], [letrasAsyncValidator]],
      apellidoPaterno: ["", [Validators.required], [letrasAsyncValidator]],
      apellidoMaterno: ["", [Validators.required], [letrasAsyncValidator]],
      curp: ["", [Validators.required], [curpAsyncValidator]],
      telefono: ["", [Validators.required],
        [telefonoAsyncValidator]],
      sexo: ["", Validators.required]
    });
    this.obtenerEmppleadoPorId();
  }

  obtenerEmppleadoPorId() {
    if (this.optionValue != null && this.optionValue.option == this.statusOperationUpate) {
      this.empleadoService.getEmpleadopoId(this.optionValue.valieModified).subscribe((response: ResponseReqeust) => {
        if (response.statusCode == this.statusResponse && response.info.length > 0) {
          this.formEmpleado.patchValue(response.info[0]);
        }
      });
    }
  }

  ngOnDestroy() {
    if (this._dialogConfirmacion) {
      if (this._dialogConfirmacion.getState() == MatDialogState.OPEN) this._dialogConfirmacion.close();
    };
  }
  save() {
    if (this.formEmpleado.valid) {
      this.empleadoService.saveEmpleado(this.formEmpleado.value).subscribe((response: ResponseReqeust) => {
        if (response.statusCode == this.statusResponse) {
          this._dialogConfirmacion.close('REG-MOD');
         this.sucessAlert();
        }else{
          this.errorMessage(ErrorUtils.MESSAGE_ERROR_REGISTRAR_EMPLEADO);
        }
      },error=>this.errorMessage(ErrorUtils.MESSAGE_ERROR_REGISTRAR_EMPLEADO));
    }
  }
  update() {
    if(this.formEmpleado.valid){
      this.formEmpleado.controls["id"].setValue(this.optionValue.valieModified);
      this.empleadoService.updateEmpleado(this.formEmpleado.value).subscribe((response: ResponseReqeust) => {
        if (response.statusCode == this.statusResponse) {
          if (this._dialogConfirmacion != undefined) {
            this._dialogConfirmacion.close('REG-MOD');
            this.sucessAlert();
          }
        }else{
          this.errorMessage(ErrorUtils.MESSAGE_ERROR_ACTUALIZAR_EMPLEADO)
        }
      },error=>this.errorMessage(ErrorUtils.MESSAGE_ERROR_ACTUALIZAR_EMPLEADO));
    }

  }

  close() {
    if (this._dialogConfirmacion != undefined) {
      this._dialogConfirmacion.close('CLOSE');
    }
  }

  sucessAlert(){
    Swal.fire({
      title: this.messageTilteOprationSucces,
      icon: "success",
      draggable: true
    });
  }
   errorMessage(mensaje: string) {
      Swal.fire({
        icon: ErrorUtils.ICON_ERRROR as SweetAlertIcon,
        text: mensaje,
      });
    }
}

export function telefonoAsyncValidator(control: AbstractControl): Observable<ValidationErrors | null> {
  const regexTelefono = /^\d{10}$/;
  if (!control.value) {
    return of(null);
  }
  return of(control.value).pipe(
    map(value => {
      return regexTelefono.exec(value) ? null : { 'numeroInvalido': true };
    })
  );
}

export function curpAsyncValidator(control: AbstractControl): Observable<ValidationErrors | null> {
  const regexCurp = /^[A-Z]{4}\d{6}[HM](AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[A-Z]{3}[A-Z\d]{2}$/;
  if (!control.value) {
    return of(null);
  }
  return of(control.value).pipe(
    map(value => {
      return regexCurp.exec(value.toUpperCase()) ? null : { 'curpInvalido': true };
    })
  );
}

export function letrasAsyncValidator(control: AbstractControl): Observable<ValidationErrors | null> {
  const regexCurp = /^[A-Za-z\s\xF1\xD1áéíóúÁÉÍÓÚ]+$/;
  if (!control.value) {
    return of(null);
  }
  return of(control.value).pipe(
    map(value => {
      return regexCurp.exec(value.toUpperCase()) ? null : { 'textoIvalido': true };
    })
  );
}
