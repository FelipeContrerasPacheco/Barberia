package com.barberia.springboot.app.controllers;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.barberia.springboot.app.models.entity.Reserva;
import com.barberia.springboot.app.models.entity.ValidReserva;
import com.barberia.springboot.app.models.service.IBarberoService;
import com.barberia.springboot.app.models.service.IBloqueHorarioService;
import com.barberia.springboot.app.models.service.IClienteService;
import com.barberia.springboot.app.models.service.IReservaService;
import com.barberia.springboot.app.models.service.IServicioService;



@Controller
@RequestMapping("/reserva")
@SessionAttributes("reserva")
public class ReservaController {
	
	@Autowired
	private IReservaService sreserva ;
	
	@Autowired
	private IClienteService scliente ;
	
	@Autowired
	private IBarberoService sbarbero ;
	
	@Autowired
	private IBloqueHorarioService sbh ;
	
	@Autowired
	private IServicioService ss ;
	
	@Secured("ROLE_USER")
	@GetMapping(value = "/form")
	public String crear(Map <String,Object> model) {
		Reserva reserva = new Reserva();
		model.put("reserva",reserva);
		model.put("titulo", "Crear Reserva");
		model.put("bloques",sbh.findAll());
		model.put("clientes",scliente.findAll());
		model.put("servicios",ss.findAll());
		return "reserva/form";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid ValidReserva reserva, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Crear Reserva");
			return "redirect:/reserva/form";
		}
		
		// Mensaje flash ni idea
		//String mensajeFlash = "Reserva creado con Exito";
		Reserva resrva = new Reserva();
		String mensajeFlash = (resrva.getId() != null)? "Cliente editado con éxito!" : "Cliente creado con éxito!";
		resrva.setBarbero(sbarbero.findOne(reserva.getBarbero()));
		resrva.setBloque(sbh.findOne(reserva.getBloque()));
		resrva.setCliente(scliente.findOne(reserva.getCliente()));
		resrva.setEstado(0);
		resrva.setFecha(reserva.getFecha());
		resrva.setPrecio(5000);
		resrva.setServicio(ss.findOne(reserva.getServicio()));
		//reserva.setFecha(vreserva.getFecha());
		sreserva.save(resrva);
		status.setComplete();
		flash.addFlashAttribute("sucess",mensajeFlash);
		return "redirect:/cliente/listar";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/reportes")
	public String reporte(Map <String,Object> model) {
		Reserva reserva = new Reserva();
		model.put("titulo", "Reportes de la Barberia Style");
		return "reserva/reportes";
	}
	
}
