package com.practica.PruebaTecnicaRest.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UuidserviceImpl implements IUuidService {

	@Override
	public String generateUUID() {
		UUID uuid = UUID.randomUUID();
		return String.valueOf(uuid);
	}

}
