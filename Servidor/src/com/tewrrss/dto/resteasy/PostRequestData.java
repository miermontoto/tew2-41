package com.tewrrss.dto.resteasy;

import com.tewrrss.dto.Post;

import javax.xml.bind.annotation.XmlElement;

public class PostRequestData extends Post {
	private String token;

	@XmlElement
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
