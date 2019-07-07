package com.barberia.springboot.app.models.service;

import java.util.List;

import com.barberia.springboot.app.models.entity.Barbero;
import com.barberia.springboot.app.models.entity.BarberoServicio;
import com.barberia.springboot.app.models.entity.Servicio;

public interface IBarberoServicioService {

	List<BarberoServicio> findAll();

	void save(BarberoServicio barbero);

	BarberoServicio findOne(Long id);

	void delete(Long id);

	List<BarberoServicio> buscarPorServicio(Servicio id);

	List<BarberoServicio> buscarPorBarbero(Barbero id);
}
