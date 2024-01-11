package com.tewrrss.dto.resteasy;

import javax.xml.bind.annotation.XmlElement;

import com.tewrrss.dto.Community;

public class CommunityRequestData extends Community {

	private static final long serialVersionUID = 6256143304361345641L;
	private String token;

	@XmlElement
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
