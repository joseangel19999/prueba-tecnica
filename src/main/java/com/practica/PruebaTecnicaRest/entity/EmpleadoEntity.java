package com.practica.PruebaTecnicaRest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="empleado")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellidoPaterno")
	private String apellidoPaterno;
	@Column(name="apellidoMaterno")
	private String apellidoMaterno;
	@Column(name="curp")
	private String curp;
	@Column(name="telefono")
	private String telefono;
	@Column(name="sexo")
	private String sexo;

}
