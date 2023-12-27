package com.tewrrss.dto;

import javax.xml.bind.annotation.XmlElement;

public class UserToken extends User {

	private static final long serialVersionUID = -7659629630214924373L;
	private String token;

	@XmlElement
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
