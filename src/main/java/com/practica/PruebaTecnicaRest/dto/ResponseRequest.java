package com.practica.PruebaTecnicaRest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseRequest {
	
	private String uuid;
	private Integer statusCode;
	private String message;
	private List<Object> info= new ArrayList<>();

}
