package com.tewrrss.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="post")
public class Post implements Serializable {
	private static final long serialVersionUID = 99834L;

	private String content;
	private String creationDate;
	private String userEmail;
	private String communityName;
	private String userName;

	public Post() { }

	public Post(String content, String creationDate, String userEmail, String communityName, String userName) {
		this.setContent(content);
		this.setCreationDate(creationDate);
		this.setUserEmail(userEmail);
		this.setCommunityName(communityName);
		this.setUserName(userName);
	}

	@XmlElement
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@XmlElement
	public String getCreationDate() {
		return creationDate.split("\\.")[0];
	}

	@XmlElement
	public String getRawCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(String string) {
		this.creationDate = string;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@XmlElement
	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@XmlElement
	public String getUserName() {
		return userName;
	}
}
