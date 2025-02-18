package com.practica.PruebaTecnicaRest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDto {
	
	private String id;
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String curp;
	private String telefono;
	private String sexo;

}
