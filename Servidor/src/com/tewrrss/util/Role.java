package com.tewrrss.util;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class Role {
	private Role() { }

	public static final int ADMIN = 0;
	public static final int USER = 1;

	public static String toString(int role) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");

		switch (role) {
			case ADMIN: return bundle.getString("role_admin");
			case USER: return bundle.getString("role_user");
			default: return bundle.getString("role_unknown");
		}
	}
}
