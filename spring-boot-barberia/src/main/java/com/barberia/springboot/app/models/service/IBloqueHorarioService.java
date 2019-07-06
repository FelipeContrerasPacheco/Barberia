package com.barberia.springboot.app.models.service;

import java.util.List;

import com.barberia.springboot.app.models.entity.BloqueHorario;

public interface IBloqueHorarioService {
	
	List<BloqueHorario> findAll() ;
	
	void save(BloqueHorario bloque);
	
	BloqueHorario findOne(Long id);
	
	void delete(Long id);

	List<BloqueHorario> buscarPorFechaYBarbero(String fecha, Long id);

	List<BloqueHorario> buscarPorFechaYServicio(String fecha, Long id);
	
}
