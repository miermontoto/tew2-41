package com.tewrrss.dto;

import javax.xml.bind.annotation.XmlElement;

public class CommunityToken extends Community{

	private String token;
	@XmlElement
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
