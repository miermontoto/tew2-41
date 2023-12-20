package com.tewrrss.infrastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GestorSesion {

	private Map<String, String> logins = new HashMap<String, String>();

	private static GestorSesion instance;
	
	private GestorSesion() {}
	
	public static GestorSesion getInstance() {
		if (instance == null)
			instance = new GestorSesion();
		return instance;
	}

	public String registrarLogin(String email) {
		String token = UUID.randomUUID().toString();
		logins.put(token, email);
		return token;
	}
	/**
	 * Retorna el email del usuario en caso de existir null en caso contrario*/
	public String checkToken(String token) {
		
		return logins.getOrDefault(token, null);
	}

}
