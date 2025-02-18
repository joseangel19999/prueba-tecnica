import { Component, Optional } from '@angular/core';
import { AfterViewInit, ViewChild } from '@angular/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { RegistroComponent } from '../registro/registro.component';
import { MatButtonModule } from '@angular/material/button';
import { EmpleadoServiceService } from '../../../../services/empleado-service.service';
import { ResponseReqeust } from '../../../../models/response';
import { EmpleadoModel } from '../../../../models/empleado';
import { GeneroPipe } from '../../../../pipes/genero.pipe';
import { OptionValueModified } from '../../../../models/options-value-modified';
import { DialogRef } from '@angular/cdk/dialog';
import Swal, { SweetAlertIcon } from 'sweetalert2';
import { ErrorUtils } from '../../../../utils/util.services';

@Component({
  selector: 'app-listado',
  standalone: true,
  imports: [
    MatPaginatorModule,
    MatPaginatorModule,
    MatTableModule,
    MatDividerModule,
    MatButtonModule,
    MatDialogModule,
    GeneroPipe
  ],
  providers: [{ provide: DialogRef, useValue: {} }],
  templateUrl: './listado.component.html',
  styleUrl: './listado.component.css'
})
export class ListadoComponent implements AfterViewInit {

  displayedColumns: string[] = ['position', 'nombre', 'apellidoPaterno', 'apellidoMaterno', 'curp', 'telefono', 'sexo', "acciones"];
  dataSource = new MatTableDataSource<EmpleadoModel>([]);
  dialogSubscripcion!: Subscription;
  empleados: EmpleadoModel[] = [];
  private autoSize: string = "auto";
  private optionValueRegister = "1";
  private optionValueUpdate = "2";
  private statusRequestSucces:number = 1;
  private valueEmptyRegisterEmpelado = "0";
  private messageTilteOprationSucces = "Operacion Exitoso";
  private value_botom_delete = "Eliminar";
  private RESULT_EVENT_MOCAL = "REG-MOD";

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private dialog: MatDialog,
    @Optional() private _dialogModalAlerta: MatDialogRef<RegistroComponent>,
    private empleadoService: EmpleadoServiceService
  ) {
    this.obtenerEmpleados();
  }

  obtenerEmpleados() {
    this.empleadoService.getEmpleados().subscribe((response: ResponseReqeust) => {
      if (response.statusCode == this.statusRequestSucces) {
        this.empleados = response.info;
        this.dataSource = new MatTableDataSource<EmpleadoModel>(this.empleados);
      }
    });
  }

  update(id: string) {
    const option = new OptionValueModified(this.optionValueUpdate, id);
    this.obtenerEmpleados();
    this._dialogModalAlerta = this.dialog.open(RegistroComponent, {
      width: this.autoSize,
      height: this.autoSize,
      data: option,
    });
    this.dialogSubscripcion = this._dialogModalAlerta
      .afterClosed()
      .subscribe((result) => {
        if (result != null && result == this.RESULT_EVENT_MOCAL) {
          this.obtenerEmpleados();
        }
      });
  }

  delete(id: string) {
    this.empleadoService.deleteEmpleado(id).subscribe((response: ResponseReqeust) => {
      if (response.statusCode = this.statusRequestSucces) {
        this.optionDeleteConfirm();
      } else {
        this.errorMessage(ErrorUtils.MESSAGE_DELETE_EMPLEADO);
      }
    }, error => this.errorMessage(ErrorUtils.MESSAGE_DELETE_EMPLEADO));
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  openModal() {
    const option = new OptionValueModified(this.optionValueRegister, this.valueEmptyRegisterEmpelado);
    this._dialogModalAlerta = this.dialog.open(RegistroComponent, {
      width: this.autoSize,
      height: this.autoSize,
      data: option,
    });
    this.dialogSubscripcion = this._dialogModalAlerta
      .afterClosed()
      .subscribe((result) => {
        if (result != null && result == this.RESULT_EVENT_MOCAL) {
          this.obtenerEmpleados();
        }
      });
  }

  errorMessage(mensaje: string) {
    Swal.fire({
      icon: ErrorUtils.ICON_ERRROR as SweetAlertIcon,
      text: mensaje,
    });
  }
  optionDeleteConfirm() {
    Swal.fire({
      title: ErrorUtils.MESSAGE_DELETE_CONFIRM_EMPLEADO,
      showCancelButton: true,
      confirmButtonText: this.value_botom_delete,
    }).then((result) => {
      if (result.isConfirmed) {
        this.successMessage();
        this.obtenerEmpleados();
      }
    });
  }
  successMessage() {
    Swal.fire({
      title: this.messageTilteOprationSucces,
      icon: "success",
      draggable: true
    });
  }
}

export interface Empleado {
  nombre: string;
  apellidoPaterno: String;
  apellidoMaterno: String;
  curp: string;
  telefono: string;
  sexo: string;
}
