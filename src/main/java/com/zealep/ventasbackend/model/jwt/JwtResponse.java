package com.zealep.ventasbackend.model.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String idTrabajador;


	public JwtResponse(String jwttoken,String idTrabajador) {
		this.jwttoken = jwttoken;
		this.idTrabajador = idTrabajador;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getIdUsuario(){
		return this.idTrabajador;
	}
}