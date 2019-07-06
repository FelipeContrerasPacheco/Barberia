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


@Secured("ROLE_USER")
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
	
	@GetMapping(value = "/form")
	public String crear(Map <String,Object> model) {
		Reserva reserva = new Reserva();
		model.put("reserva",reserva);
		model.put("titulo", "Crear Reserva");
		return "reserva/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Reserva reserva, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Crear Reserva");
			return "redirect:/reserva/form";
		}
		// Mensaje flash ni idea
		String mensajeFlash = "Reserva creado con Exito";
		
		//reserva.setFecha(vreserva.getFecha());
		sreserva.save(reserva);
		status.setComplete();
		flash.addFlashAttribute("sucess",mensajeFlash);
		return "redirect:/cliente/listar";
	}
}
