package com.practica.PruebaTecnicaRest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.PruebaTecnicaRest.entity.EmpleadoEntity;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity,Long> {

	public List<EmpleadoEntity> findAll();
	public Optional<EmpleadoEntity> findById(Long id);
	public Optional<EmpleadoEntity> findByNombre(String nombre);
}
