package com.barberia.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barberia.springboot.app.models.entity.Barbero;
import com.barberia.springboot.app.models.entity.BarberoServicio;
import com.barberia.springboot.app.models.entity.Servicio;

public interface IBarberoServicioDao extends JpaRepository<BarberoServicio, Long>{
	
	public List<BarberoServicio> findByBarbero(Barbero barbero);
	
	// este busca por servicio!!
	public List<BarberoServicio> findByServicio(Servicio servicio);
}
