package com.mkyong.rest;

import java.io.Serializable;

public class Factura implements Serializable{
	private static final long serialVersionUID = -546761926706076123L;

	public String id;
	public String nombre;
	public String vencimiento;
	public String totalGeneral;
	
	public Factura(String id, String nombre, String vencimiento, String totalGeneral) {
		this.id = id;
		this.nombre = nombre;
        this.vencimiento = vencimiento;
        this.totalGeneral = totalGeneral;
	}
}
