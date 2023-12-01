package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.tewrrss.business.CommunityService;
import com.tewrrss.business.UserService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

@ManagedBean(name = "users")
@SessionScoped
public class BeanUsers implements Serializable {
	private static final long serialVersionUID = 5128381238225556L;

	private UserService userService;
	private CommunityService communityService;

	private String userEmail;
	private String communityName;

	public BeanUsers() {
		userService = Factories.services.createUserService();
		communityService = Factories.services.createCommunityService();
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCommunityName() {
		return this.communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String join() {
		Optional<User> user = userService.findByEmail(userEmail);
		Optional<Community> community = communityService.findByName(communityName);

		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		if (!user.isPresent() || !community.isPresent()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("members_add_error_null"), null));
			return "error";
		}

		String result = communityService.join(community.get(), user.get());
		if (result.equals("unable")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("members_add_error_unable"), null));
			return "unable";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("members_add_ok"), null));
		return result;
	}

	public List<String> listEmails() {
		return userService.listAll().stream().map(u -> u.getEmail()).collect(Collectors.toList());
	}

	public List<String> listCommunities() {
		return communityService.listAll().stream().map(c -> c.getName()).collect(Collectors.toList());
	}
}
