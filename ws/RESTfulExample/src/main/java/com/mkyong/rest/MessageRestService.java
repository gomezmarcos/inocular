package com.mkyong.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
public class MessageRestService {

	@GET
	@Path("/everis/{param}")
	public Response printMessage(@PathParam("param") String apellido) {
			List<Persona> personas = Collections.EMPTY_LIST;
			try {
				personas =  retrievePersonaByApellido(apellido);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Gson gson = new GsonBuilder().create();
	    
	    return Response.status(200).entity(gson.toJson(personas)).build();
	}

	@GET
	@Path("/test")
	public Response printMessage3() {
			List<Persona> personas = new ArrayList<Persona>();
				personas.add(new Persona("30321188", "Marcos Gomez"));
				personas.add(new Persona("33397167", "Nuria Valentini"));
				personas.add(new Persona("12332145", "Pepe Sand"));
			
			Gson gson = new GsonBuilder().create();
	    
	    return Response.status(200).entity(gson.toJson(personas)).build();
	}

	@GET
	@Path("/facturas/nombre/{param}")
	public Response printMessage2(@PathParam("param") String nombre) {
			List<Factura> facturas = new ArrayList<Factura>();
			try {
				facturas =  retrieveFacturasPorNombre(nombre);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Gson gson = new GsonBuilder().create();
	    
	    return Response.status(200).entity(gson.toJson(facturas)).build();
	}

	private List<Persona> retrievePersonaByApellido(String apellido) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@7.217.102.76:1521:xe","RCE_GED","rce_gd");  

		Statement stmt=con.createStatement();  

		ResultSet rs = stmt.executeQuery(String.format("select * from RCE_PERSONA p inner join SYS_SEXO s on p.fk_sexo = s.id_sexo WHERE PRIMER_APELLIDO = '%s'", apellido));
		
		List<Persona> personas = new ArrayList<Persona>();
		while(rs.next())   {
			String rsNombre = rs.getNString("PRIMER_NOMBRE");
			String rsDni = rs.getNString("NUMERO_DOCUMENTO");
			String rsInformeFallecimiento = rs.getNString("DESCRIPCION");
			System.out.println(rsInformeFallecimiento);
			personas.add(new Persona(rsDni, rsNombre));
		}
		con.close();

		return personas;
	}

	private List<Factura> retrieveFacturasPorNombre(String nombre) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@192.168.2.201:1521:INO0","web","1n0cul4r");  

		Statement stmt=con.createStatement();  

		ResultSet rs = stmt.executeQuery("select * from v_facturas where id_factura < 50 ");
		
		List<Factura> facturas = new ArrayList<Factura>();
		while(rs.next())   {
			String rsNombre = rs.getNString("NOMBRE");
//			String rsId = rs.getNString("ID_FACTURA");
//			String rsTotalGeneral = rs.getNString("TOTAL_GENERAL");
//			String rsVencimiento = rs.getNString("VENCIMIENTO");
			facturas.add(new Factura("rsId", rsNombre, "rsTotalGeneral", "rsVencimiento"));
//			facturas.add(new Factura(rsId, rsNombre, rsTotalGeneral, rsVencimiento));
		}
		con.close();

		return facturas;
	}
}
