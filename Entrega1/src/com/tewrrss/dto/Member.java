package com.tewrrss.dto;

public class Member {
	
	private Community community;
	private User user;
	
	public Member() { }
	
	public Member(Community community, User user) {
		this.community = community;
		this.user = user;
	}

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

}
