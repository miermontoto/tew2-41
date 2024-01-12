package com.tewrrss.dto.resteasy;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

import javax.xml.bind.annotation.XmlElement;

public class MemberRequestData  {
	private static final long serialVersionUID = 4522139807580927869L;
	private Community community;
	private User user;
	private String token;

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@XmlElement
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
