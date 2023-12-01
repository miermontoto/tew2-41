package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.*;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.util.Role;

@ManagedBean(name = "user")
@SessionScoped
public class BeanUser implements Serializable {
	private static final long serialVersionUID = 51283812385556L;

	private User user;

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCommunities() {
		return Factories.services.createCommunityService().listJoined(user).stream().map(Community::getName)
			.reduce((a, b) -> a + ", " + b).orElse("");
	}

	public int getNumberOfPosts() {
		return Factories.services.createPostService().getPostsByUser(user).size();
	}

	public String getRole() {
		return Role.toString(user.getRole());
	}

	public String getName() {
		return user.getUsername();
	}

	public String getEmail() {
		return user.getEmail();
	}

	public List<Post> listPosts() {
		return Factories.services.createPostService().getPostsByUser(user);
	}

	public User findByEmail(String email) {
		Optional<User> result = Factories.services.createUserService().findByEmail(email);
		if (result.isPresent()) {
			return result.get();
		}

		return null;
	}

	public boolean displayActions() {
		User loggedUser = new BeanInfo().getSessionUser();
		return loggedUser.getRole() == Role.ADMIN || user.getEmail().equals(loggedUser.getEmail());
	}
}
