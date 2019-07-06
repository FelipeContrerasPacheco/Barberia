package com.barberia.springboot.app.models.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name ="reserva")
public class Reserva implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fecha ;
    
	@OneToOne
	private BloqueHorario bloque ;
	
	@ManyToOne
	private Cliente cliente ;
	
	@ManyToOne
	private Servicio servicio ;
	@OneToOne
	private Barbero barbero ;
	 
	private int precio ;
	
	private int estado ;
	
	public Reserva() {
		
	}

	public Reserva(Long id,String fecha, BloqueHorario bloque, Cliente cliente, Servicio servicio, Barbero barbero,
			int precio, int estado) {
		this.id = id;
		this.fecha = fecha;
		this.bloque = bloque;
		this.cliente = cliente;
		this.servicio = servicio;
		this.barbero = barbero;
		this.precio = precio;
		this.estado = estado ;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public BloqueHorario getBloque() {
		return bloque;
	}

	public void setBloque(BloqueHorario bloque) {
		this.bloque = bloque;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Barbero getBarbero() {
		return barbero;
	}

	public void setBarbero(Barbero barbero) {
		this.barbero = barbero;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	

	
	
	
}
