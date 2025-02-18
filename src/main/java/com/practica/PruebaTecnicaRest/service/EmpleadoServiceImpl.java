package com.practica.PruebaTecnicaRest.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practica.PruebaTecnicaRest.dto.EmpleadoDto;
import com.practica.PruebaTecnicaRest.dto.ResponseRequest;
import com.practica.PruebaTecnicaRest.dto.SaveEmpleadoDto;
import com.practica.PruebaTecnicaRest.entity.EmpleadoEntity;
import com.practica.PruebaTecnicaRest.mapper.Mapper;
import com.practica.PruebaTecnicaRest.repository.EmpleadoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements IEmpleadoService{
	
	private final EmpleadoRepository empleadoRepository;
	private final IUuidService uuidService;
	private Integer estatusCodeSuccess=1;
	private Integer estatusCodeFailed=0;
	private String messageSeccess="Proceso exitoso";
	private String messageFailed="Proceso no exitoso";
	

	@Override
	public ResponseRequest saveEmpleado(SaveEmpleadoDto empleado) {
		ResponseRequest response= new ResponseRequest();
		try {
			EmpleadoEntity empleadoEntity=Mapper.mapperSaveEmpleadoDtoToEmpleadoEntity(empleado);
			this.empleadoRepository.save(empleadoEntity);
			response.setUuid(uuidService.generateUUID());
			response.setStatusCode(estatusCodeSuccess);
			response.setMessage(messageSeccess);
		}catch (Exception e) {
			log.info("Error al guardar el empleado");
			response.setUuid(uuidService.generateUUID());
			response.setStatusCode(estatusCodeFailed);
			response.setMessage(messageFailed);
		}
		return response;
	}

	@Override
	public ResponseRequest updateEmpleado(EmpleadoDto empleado) {
		ResponseRequest response= new ResponseRequest();
		try {
			Optional<EmpleadoEntity> empleadoEntity=this.empleadoRepository.findById(Long.parseLong(empleado.getId()));
			if(empleadoEntity.isPresent()) {
				EmpleadoEntity empleadoUpdate=Mapper.mapperEmpleadoDtoToEmpleadoEntity(empleado);
				empleadoUpdate.setId(Long.parseLong(empleado.getId()));
				this.empleadoRepository.save(empleadoUpdate);
				response.getInfo().add(empleado);
				response.setUuid(uuidService.generateUUID());
				response.setStatusCode(estatusCodeSuccess);
				response.setMessage(messageSeccess);
			}
		}catch (Exception e) {
			response.setUuid(uuidService.generateUUID());
			response.setStatusCode(estatusCodeFailed);
			response.setMessage(messageFailed);
		}
		return response;
	}

	@Override
	public ResponseRequest getEmpleado(String idEmpleado) {
		ResponseRequest response= new ResponseRequest();
		try {
			Optional<EmpleadoEntity> empleado=this.empleadoRepository.findById(Long.parseLong(idEmpleado));
			response.setUuid(uuidService.generateUUID());
			response.setStatusCode(estatusCodeSuccess);
			response.setMessage(messageSeccess);
			if(empleado.isPresent()) {
				List<EmpleadoDto> emppleadosResponse=Arrays.asList(Mapper.mapperEmpleadoDto(empleado.get()));
				response.getInfo().addAll(emppleadosResponse);
			}
		}catch (Exception e) {
			response.setUuid(uuidService.generateUUID());
			response.setStatusCode(estatusCodeFailed);
			response.setMessage(messageFailed);
		}
		return response;
	}

	@Override
	public ResponseRequest getEmpleados() {
		ResponseRequest response= new ResponseRequest();
		try {
			List<EmpleadoEntity> empleados=this.empleadoRepository.findAll();
			List<EmpleadoDto> emppleadosResponse=Mapper.mapperEmpleadoEntityListToListEmpleadoDto(empleados);
			response.setUuid(uuidService.generateUUID());
			response.setStatusCode(estatusCodeSuccess);
			response.setMessage(messageSeccess);
			response.getInfo().addAll(emppleadosResponse);
		}catch (Exception e) {
			response.setUuid(uuidService.generateUUID());
			response.setStatusCode(estatusCodeFailed);
			response.setMessage(messageFailed);
		}
		return response;
	}

	@Override
	public ResponseRequest deleteEmpelado(String id) {
		ResponseRequest response= new ResponseRequest();
		try {
			Optional<EmpleadoEntity> empleado=this.empleadoRepository.findById(Long.parseLong(id));
			if(empleado.isPresent()) {
				this.empleadoRepository.delete(empleado.get());
				response.setUuid(uuidService.generateUUID());
				response.setStatusCode(estatusCodeSuccess);
				response.setMessage(messageSeccess);
			}else {
				response.setUuid(uuidService.generateUUID());
				response.setStatusCode(estatusCodeFailed);
				response.setMessage(messageFailed);
			}
		}catch (Exception e) {
			log.info("Error al guardar el empleado");
			response.setUuid(uuidService.generateUUID());
			response.setStatusCode(estatusCodeFailed);
			response.setMessage(messageFailed);
		}
		return response;
	}

}
