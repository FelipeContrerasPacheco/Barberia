package com.barberia.springboot.app.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.barberia.springboot.app.models.entity.Barbero;
import com.barberia.springboot.app.models.service.IBarberoService;

@RestController
@CrossOrigin(origins="localhost:8080", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/barberoql")
public class RCBarbero {
	
	@Autowired
	IBarberoService servicio ;
	
	@GetMapping("/listar")
	public List<Barbero> listar(){
		return servicio.findAll() ;
	}
}
