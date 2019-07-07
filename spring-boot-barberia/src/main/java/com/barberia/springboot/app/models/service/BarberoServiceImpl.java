package com.barberia.springboot.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.springboot.app.models.dao.IBarberoDao;
import com.barberia.springboot.app.models.entity.Barbero;
import com.barberia.springboot.app.models.entity.BarberoServicio;
import com.barberia.springboot.app.models.entity.Servicio;
@Service
public class BarberoServiceImpl implements IBarberoService{
	@Autowired
	private IBarberoDao barberoDao;
	
	@Autowired
	private BarberoServicioServiceImp bss ;
	
	@Autowired
	private ServicioServiceImpl ss ;

	@Override
	public List<Barbero> findAll() {
		return barberoDao.findAll();
	}

	@Override
	public void save(Barbero barbero) {
		barberoDao.save(barbero);
	}

	@Override
	public Barbero findOne(Long id) {
		return barberoDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		barberoDao.deleteById(id);
	}
	
	public List<Barbero> buscarporServicio(Long idServicio){
		List<Barbero> aux = new ArrayList<Barbero>();
		for(BarberoServicio bar : bss.buscarPorServicio(ss.findOne(idServicio))) {
			aux.add(bar.getBarbero());
		}
		return aux ;
	}
	public List<Servicio> buscarporBarbero(Long idBarbero){
		List<Servicio> aux = new ArrayList<Servicio>();
		for(BarberoServicio bar : bss.buscarPorBarbero(findOne(idBarbero))) {
			aux.add(bar.getServicio());
		}
		return aux ;
	}
	
}
