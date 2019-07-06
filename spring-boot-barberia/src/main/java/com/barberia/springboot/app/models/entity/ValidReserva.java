package com.barberia.springboot.app.models.entity;

public class ValidReserva {
	private String fecha ;
	private Long bloque ;
	private Long cliente ;
	private Long servicio ;
	private Long barbero ;
	
	public ValidReserva(){}

	public ValidReserva(String fecha, Long bloque, Long cliente, Long servicio, Long barbero) {
		this.fecha = fecha;
		this.bloque = bloque;
		this.cliente = cliente;
		this.servicio = servicio;
		this.barbero = barbero;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Long getBloque() {
		return bloque;
	}

	public void setBloque(Long bloque) {
		this.bloque = bloque;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public Long getServicio() {
		return servicio;
	}

	public void setServicio(Long servicio) {
		this.servicio = servicio;
	}

	public Long getBarbero() {
		return barbero;
	}

	public void setBarbero(Long barbero) {
		this.barbero = barbero;
	}
	
	
}
