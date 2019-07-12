package com.barberia.springboot.app.models.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barberia.springboot.app.models.dao.BloqueHorarioDao;
import com.barberia.springboot.app.models.dao.IReservaDao;
import com.barberia.springboot.app.models.entity.Barbero;
import com.barberia.springboot.app.models.entity.BarberoServicio;
import com.barberia.springboot.app.models.entity.BloqueHorario;
import com.barberia.springboot.app.models.entity.Reserva;
import com.barberia.springboot.app.models.entity.Servicio;

@Service
public class BloqueHorarioServiceImp implements IBloqueHorarioService {
	@Autowired
	BloqueHorarioDao bloquehorario ;
	
	@Autowired
	IReservaDao reserva ;
	
	@Autowired
	IBarberoService barbero ;
	
	@Autowired
	IServicioService servicio ;
	
	@Autowired
	IBarberoServicioService bss ;

	@Override
	public List<BloqueHorario> findAll() {
		return bloquehorario.findAll();
	}
	
	// Carga las horas disponibles del barbero por fecha
	
	@Override
	public void save(BloqueHorario bloque) {
		bloquehorario.save(bloque);
	}

	@Override
	public BloqueHorario findOne(Long id) {
		return bloquehorario.findByIdBloque(id);
	}

	@Override
	public void delete(Long id) {
		bloquehorario.deleteById(id);
	}

	

	@Override
	public List<BloqueHorario> buscarPorFechaYServicio(String fecha, Long id) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		List<BarberoServicio> barbeservice = bss.buscarPorServicio(servicio.findOne(id));
		List<Barbero> barberos = new ArrayList<Barbero>();
		List<BloqueHorario> vacio = new ArrayList<BloqueHorario>();
		List<BloqueHorario> todos = bloquehorario.findAll();
		for(BarberoServicio aux : barbeservice) {
			barberos.add(aux.getBarbero());
		}
		for(Barbero aux : barberos) {
			reservas.addAll(reserva.findByFechaAndBarberoAndEstado(fecha,barbero.findOne(aux.getId()),0));
		}
		for(Reserva aux : reservas) {
			vacio.add(aux.getBloque());
		}
		todos.removeAll(vacio);
		return todos ;
	}

	@Override
	public List<BloqueHorario> buscarPorFechaYBarbero(String fecha, Long id) {
		List<Reserva> reservas = reserva.findByFechaAndBarberoAndEstado(fecha,barbero.findOne(id),0);
		List<BloqueHorario> vacio = new ArrayList<BloqueHorario>();
		for(Reserva aux : reservas) {
			vacio.add(aux.getBloque());
		}
		List<BloqueHorario> todos = bloquehorario.findAll();
		todos.removeAll(vacio);
		return todos ;
	}
	
}
