package com.tewrrss.dto.resteasy;

import com.tewrrss.dto.Community;

import javax.xml.bind.annotation.XmlElement;

public class CommunityRequestData extends Community {
	private String token;

	@XmlElement
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
