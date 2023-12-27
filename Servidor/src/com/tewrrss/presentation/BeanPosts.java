package com.tewrrss.presentation;

import java.time.LocalDateTime;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.tewrrss.business.PostService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

@ManagedBean(name = "posts")
@SessionScoped
public class BeanPosts {
	private String content;
	private BeanInfo loginInfo;
	private PostService service;
	private Community currentCommunity;

	public BeanPosts() {
		service = Factories.services.createPostService();
		loginInfo = new BeanInfo();
	}

	public void setCurrentCommunity(Community community) {
		this.currentCommunity = community;
	}

	public Community getCurrentCommunity() {
		return this.currentCommunity;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String remove(Post mensaje) {
		User user = loginInfo.getSessionUser();
		if (service.ableToRemove(mensaje, user)) {
			return service.remove(mensaje);
		}

		return "unauthorized";
	}

	public String add() {
		User user = loginInfo.getSessionUser();
		if (!content.isEmpty()) {
			String temp = content;
			content = "";
	        return service.add(new Post(temp, LocalDateTime.now().toString().replace('T', ' '), user.getEmail(), currentCommunity.getName()));
        }

		return "error";
	}

	public List<Post> getNewPosts() {
		return service.getNewPosts(loginInfo.getSessionUser());
	}

	public List<Post> getPostsByUser() {
		return service.getPostsByUser(loginInfo.getSessionUser());
	}

	public List<Post> getPostsInCommunity() {
		return service.getPostsInCommunity(currentCommunity);
	}

}
