package com.barberia.springboot.app.models.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.springboot.app.models.dao.IBarberoServicioDao;
import com.barberia.springboot.app.models.entity.BarberoServicio;
import com.barberia.springboot.app.models.entity.Servicio;

@Service
public class BarberoServicioServiceImp implements IBarberoServicioService{
	
	@Autowired
	private IBarberoServicioDao bsDao ;

	@Override
	public List<BarberoServicio> findAll() {
		return bsDao.findAll();
	}

	@Override
	public void save(BarberoServicio barbero) {
		bsDao.save(barbero);
	}

	@Override
	public BarberoServicio findOne(Long id) {
		return bsDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		bsDao.deleteById(id);
	}
	

	@Override
	public List<BarberoServicio> buscarPorServicio(Servicio servicio) {
		return bsDao.findByServicio(servicio);
	}
	
}
