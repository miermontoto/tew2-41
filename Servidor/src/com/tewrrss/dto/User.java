package com.tewrrss.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tewrrss.util.Role;

@XmlRootElement(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 56556L;

	private String email;
	private String username;
	private String password;
	private int role;

	public User() { }

	public User(String email, String username, String password, int role) {
		this.setEmail(email);
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
	}

	public User(String email, String username, String password) {
		this(email, username, password, Role.USER);
	}

	public User(String email, String username) {
		this(email, username, Role.USER);
	}

	public User(String email, String username, int role) {
		this(email, username, "", role);
	}

	@XmlElement
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		if (role != Role.ADMIN && role != Role.USER)
			throw new IllegalArgumentException(String.format("Invalid role: %d", role));

		this.role = role;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@XmlElement
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
