package com.tewrrss.dto.resteasy;

import javax.xml.bind.annotation.XmlElement;

public class CommunityRequestData {
	private String token;
	private String name;
	private String description;

	public CommunityRequestData() {
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Name cannot be null or empty");

		this.name = name;
	}

	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null || description.isEmpty())
			throw new IllegalArgumentException("Description cannot be null or empty");

		this.description = description;
	}

	@XmlElement
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
