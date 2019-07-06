package com.barberia.springboot.app.controllers;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.barberia.springboot.app.models.entity.Barbero;
import com.barberia.springboot.app.models.service.IBarberoService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/barbero")
@SessionAttributes("barbero")
public class barberoController {
	
	@Autowired
	private IBarberoService barberoService;
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de barberos");
		model.addAttribute("barberos", barberoService.findAll());
		return "barbero/listar";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Barbero barbero = new Barbero();
		model.put("barbero", barbero);
		model.put("titulo", "Crear barbero");
		return "barbero/form";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Barbero barbero = null;
		
		if(id > 0) {
			barbero = barberoService.findOne(id);
			if(barbero == null) {
				flash.addFlashAttribute("error", "El ID del barbero no existe en la BBDD!");
				return "redirect:/barbero/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del barbero no puede ser cero!");
			return "redirect:/barbero/listar";
		}
		model.put("barbero", barbero);
		model.put("titulo", "Editar Barbero");
		return "barbero/form";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Barbero barbero, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear Barbero");
			return "barbero/form";
		}
		String mensajeFlash = (barbero.getId() != null)? "Barbero editado con éxito!" : "Barbero creado con éxito!";
		// Aqui hay que buscar los objetos!!!! ? 
		barberoService.save(barbero);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/barbero/listar";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			barberoService.delete(id);
			flash.addFlashAttribute("success", "Barbero eliminado con éxito!");
		}
		return "redirect:/barbero/listar";
	}
	
	//@Secured({"ROLE_ADMIN","ROLE_ANONYMOUS"})
	@PreAuthorize("isAnonymous() or isFullyAuthenticated()")
	@RequestMapping(path = "/listarbarberos", produces="application/json")
	@ResponseBody
	
	public List<Barbero> listarBarberoCtm(){
		return barberoService.findAll() ;
	}
	
	//@RequestMapping(path = "/listarbarberosservicios", produces="application/json")
	//@ResponseBody
	//public List<Barbero> listarBarberoyServicios(){
	//	return barberoService.buscarporServicio(2L) ;
	//}
	
    @PostMapping(value = "/listarbarberosservicios", produces = {"application/json"})
    public @ResponseBody
    List<Barbero> cargarBarberos(@RequestBody String json) throws Exception {
    	HashMap result = new ObjectMapper().readValue(json, HashMap.class);
        return  barberoService.buscarporServicio(Long.parseLong((String) result.get("id")));
    }

	
}
