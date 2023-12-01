package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.util.Role;

@ManagedBean(name = "info")
@SessionScoped
public class BeanInfo implements Serializable {
	private static final long serialVersionUID = 55556L;

	public User getSessionUser() {
		return (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGGEDIN_USER");
	}

	public String getSessionUsername() {
		return getSessionUser().getUsername();
	}

	public int getSessionRole() {
		return getSessionUser().getRole();
	}

	public String getSessionRoleName() {
		return Role.toString(this.getSessionRole());
	}

	public String resetDatabase() {
		boolean status = Factories.services.createDatabaseService().reset();
		if (!status) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,
				bundle.getString("error_reset_db"), null));
			return "error";
		}

		return "success";
	}
}
