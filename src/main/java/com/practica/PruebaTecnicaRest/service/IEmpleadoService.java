package com.practica.PruebaTecnicaRest.service;

import com.practica.PruebaTecnicaRest.dto.EmpleadoDto;
import com.practica.PruebaTecnicaRest.dto.ResponseRequest;
import com.practica.PruebaTecnicaRest.dto.SaveEmpleadoDto;

public interface IEmpleadoService {
	
	public ResponseRequest saveEmpleado(SaveEmpleadoDto empleado);
	public ResponseRequest updateEmpleado(EmpleadoDto empleado);
	public ResponseRequest getEmpleado(String idEmpleado);
	public ResponseRequest getEmpleados();
	public ResponseRequest deleteEmpelado(String id);

}
