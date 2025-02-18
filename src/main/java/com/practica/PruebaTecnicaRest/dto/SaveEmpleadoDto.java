package com.practica.PruebaTecnicaRest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveEmpleadoDto {

	private String nombre;
	@NotBlank(message = "El nombre es obligatorio")
	@NotEmpty(message="El nombre es obligatorio")
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String curp;
	private String telefono;
	private String sexo;
}
