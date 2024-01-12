package com.tewrrss.infrastructure;

import java.util.TreeMap;
import java.util.Map;
import java.util.UUID;

import com.tewrrss.dto.User;

public class GestorSesion {

	private Map<String, User> logins = new TreeMap<>();
	private static GestorSesion instance;

	private GestorSesion() {}

	public static GestorSesion getInstance() {
		if (instance == null) instance = new GestorSesion();
		return instance;
	}

	public String register(User u) {
		String token = UUID.randomUUID().toString();
		logins.put(token, u);
		return token;
	}

	public String expire(String token) {
		return logins.remove(token) != null ? "success" : "invalid";
	}

	public User getUser(String token) {
		return logins.getOrDefault(token, null);
	}

}
