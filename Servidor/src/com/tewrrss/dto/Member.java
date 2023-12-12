package com.tewrrss.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="member")
public class Member {
	
	private Community community;
	private User user;
	
	public Member() { }
	
	public Member(Community community, User user) {
		this.community = community;
		this.user = user;
	}

	@XmlElement
	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	@XmlElement
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
