package com.tewrrss.dto.resteasy;

import com.tewrrss.dto.User;

import javax.xml.bind.annotation.XmlElement;

public class UserRequestData extends User {
	private String token;

	@XmlElement
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
