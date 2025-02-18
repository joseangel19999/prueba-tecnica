package com.practica.PruebaTecnicaRest.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.practica.PruebaTecnicaRest.dto.EmpleadoDto;
import com.practica.PruebaTecnicaRest.dto.SaveEmpleadoDto;
import com.practica.PruebaTecnicaRest.entity.EmpleadoEntity;

public class Mapper {
	
	public static EmpleadoEntity mapperSaveEmpleadoDtoToEmpleadoEntity(SaveEmpleadoDto empleadoDto) {
		return EmpleadoEntity.builder().nombre(empleadoDto.getNombre())
				.apellidoPaterno(empleadoDto.getApellidoPaterno())
				.apellidoMaterno(empleadoDto.getApellidoMaterno())
				.curp(empleadoDto.getCurp())
				.telefono(empleadoDto.getTelefono())
				.sexo(empleadoDto.getSexo()).build();
	}
	
	public static EmpleadoEntity mapperEmpleadoDtoToEmpleadoEntity(EmpleadoDto empleadoDto) {
		return EmpleadoEntity.builder().nombre(empleadoDto.getNombre())
				.apellidoPaterno(empleadoDto.getApellidoPaterno())
				.apellidoMaterno(empleadoDto.getApellidoMaterno())
				.curp(empleadoDto.getCurp())
				.telefono(empleadoDto.getTelefono())
				.sexo(empleadoDto.getSexo()).build();
	}

	public static List<EmpleadoDto> mapperEmpleadoEntityListToListEmpleadoDto(List<EmpleadoEntity> empleados) {
		return empleados.stream().map(empleado->Mapper.mapperEmpleadoDto(empleado)).collect(Collectors.toList());
	}
	
	public static EmpleadoDto mapperEmpleadoDto(EmpleadoEntity empleado) {
		return EmpleadoDto.builder().id(empleado.getId().toString()).nombre(empleado.getNombre())
				.apellidoPaterno(empleado.getApellidoPaterno())
				.apellidoMaterno(empleado.getApellidoMaterno())
				.curp(empleado.getCurp())
				.telefono(empleado.getTelefono())
				.sexo(empleado.getSexo()).build();
	}
}
