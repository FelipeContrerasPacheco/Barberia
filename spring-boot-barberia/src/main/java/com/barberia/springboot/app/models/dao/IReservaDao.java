package com.barberia.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barberia.springboot.app.models.entity.Barbero;
import com.barberia.springboot.app.models.entity.Reserva;

public interface IReservaDao extends JpaRepository<Reserva, Long> {
	
	public List<Reserva> findByFechaAndBarberoAndEstado(String fecha, Barbero barbero, int estado); 
	
	public List<Reserva> findByFecha(String fecha); // Fecha Date -> between
}
