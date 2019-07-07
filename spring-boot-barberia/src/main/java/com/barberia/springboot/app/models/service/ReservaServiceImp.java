package com.barberia.springboot.app.models.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barberia.springboot.app.models.dao.IReservaDao;
import com.barberia.springboot.app.models.entity.Reserva;

@Service
public class ReservaServiceImp implements IReservaService{

	@Autowired
	private IReservaDao reservaDao ;
	
	@Override
	public List<Reserva> findAll() {
		return reservaDao.findAll() ;
	}

	@Override
	public void save(Reserva reserva) {
		reservaDao.save(reserva);
	}

	@Override
	public Reserva findOne(Long id) {
		return reservaDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		reservaDao.deleteById(id);
	}

	@Override
	public List<Object> findTopBarbero() {
		// TODO Auto-generated method stub
		return reservaDao.findTopBarbero();
	}

	@Override
	public List<Object> findTopServicios() {
		// TODO Auto-generated method stub
		return reservaDao.findTopServicios();
	}

	

	


}
