package com.mkyong.rest;

import java.io.Serializable;

public class Persona implements Serializable{
	private static final long serialVersionUID = -546761926706076824L;

	public String dni;
	public String nombreCompleto;

	public Persona(String dni, String nombreCompleto) {
		this.dni = dni;
		this.nombreCompleto = nombreCompleto;
	}
}