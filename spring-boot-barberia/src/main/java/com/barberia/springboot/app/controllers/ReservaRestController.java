package com.barberia.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barberia.springboot.app.models.service.IReservaService;


@RestController
@RequestMapping("/api")
public class ReservaRestController {
	
	@Autowired
	private IReservaService reservaService;
	
	@GetMapping("/topBarberos")
	public List<Object> TopBarberos(){
		return 	reservaService.findTopBarbero();
	}
	
	@GetMapping("/topServicios")
	public List<Object> sMasSolicitados(){
		return reservaService.findTopServicios();
	}

}
