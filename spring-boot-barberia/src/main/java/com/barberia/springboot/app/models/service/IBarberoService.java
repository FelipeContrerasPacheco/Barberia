package com.barberia.springboot.app.models.service;

import java.util.List;

import com.barberia.springboot.app.models.entity.Barbero;
import com.barberia.springboot.app.models.entity.Servicio;

public interface IBarberoService {

	List<Barbero> findAll();

	void save(Barbero barbero);

	Barbero findOne(Long id);

	void delete(Long id);
	
	List<Barbero> buscarporServicio(Long idServicio);
	List<Servicio> buscarporBarbero(Long idBarbero);

}
