package com.practica.PruebaTecnicaRest.exceptionHandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.practica.PruebaTecnicaRest.dto.ResponseRequest;
import com.practica.PruebaTecnicaRest.service.IUuidService;

@RestControllerAdvice
public class CustomExceptionHandler {
	private Integer estatusCodeFailed=0;
	private String messageFailed="Proceso no exitoso";
	
	@Autowired
	private IUuidService uuidService;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseRequest handleValidationExceptions(MethodArgumentNotValidException ex) {
		ResponseRequest response= new ResponseRequest();
		
        Map<String, String> errores = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errores.put(error.getField(), error.getDefaultMessage())
        );
        response.setUuid(uuidService.generateUUID());
		response.setStatusCode(estatusCodeFailed);
		response.setMessage(messageFailed);
		response.getInfo().addAll(new ArrayList<>(errores.values()));
        return response;
    }
}
