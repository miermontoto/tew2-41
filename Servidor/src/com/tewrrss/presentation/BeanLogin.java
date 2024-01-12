package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tewrrss.business.LoginService;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

@ManagedBean(name = "login")
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 2L;

	private String name = "";
	private String password = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public String verify() {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		LoginService login = Factories.services.createLoginService();

		User user = login.verify(name, password);
		if (user != null) {
			BeanLogin.putUserInSession(user);
			return "success";
		}

		FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("error_login_unknown"), null);
		jsfCtx.addMessage(null, msgs);

		return "login";
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}

	protected static void putUserInSession(User user) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("LOGGEDIN_USER", user);
	}
}
