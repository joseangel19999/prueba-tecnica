package com.practica.PruebaTecnicaRest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practica.PruebaTecnicaRest.dto.EmpleadoDto;
import com.practica.PruebaTecnicaRest.dto.ResponseRequest;
import com.practica.PruebaTecnicaRest.dto.SaveEmpleadoDto;
import com.practica.PruebaTecnicaRest.service.IEmpleadoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/empleado/")
@RequiredArgsConstructor
public class EmpleadoController {
	
	private final IEmpleadoService empleadoService;
	
	@PostMapping("save")
	public ResponseEntity<ResponseRequest> save(@Valid @RequestBody SaveEmpleadoDto empleado){
		return new ResponseEntity<ResponseRequest>(this.empleadoService.saveEmpleado(empleado),HttpStatus.OK);
	};
	
	@GetMapping
	public ResponseEntity<ResponseRequest> empleados(){
		return new ResponseEntity<ResponseRequest>(this.empleadoService.getEmpleados(),HttpStatus.OK);
	};
	
	@GetMapping("search")
	public ResponseEntity<ResponseRequest> empleado(@RequestParam("id") String id){
		return new ResponseEntity<ResponseRequest>(this.empleadoService.getEmpleado(id),HttpStatus.OK);
	};
	
	@PostMapping("update")
	public ResponseEntity<ResponseRequest> update(@RequestBody EmpleadoDto empleado){
		return new ResponseEntity<ResponseRequest>(this.empleadoService.updateEmpleado(empleado),HttpStatus.OK);
	};
	
	@DeleteMapping
	public ResponseEntity<ResponseRequest> delete(@RequestParam("id") String id){
		return new ResponseEntity<ResponseRequest>(this.empleadoService.deleteEmpelado(id),HttpStatus.OK);
	};

}
