package com.tewrrss.dto;

import java.io.Serializable;

public class Community implements Serializable {
	private static final long serialVersionUID = 88282L;

	private String name;
	private String description;

	public Community() { }

	public Community(String name, String description) {
		this.setName(name);
		this.setDescription(description);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Name cannot be null or empty");

		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null || description.isEmpty())
			throw new IllegalArgumentException("Description cannot be null or empty");

		this.description = description;
	}
}
