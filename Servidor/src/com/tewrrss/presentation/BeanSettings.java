package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "settings")
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");

	public Locale getLocale() {
		return locale;
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		} catch (Exception ex) {
			FacesContext jsfCtx = FacesContext.getCurrentInstance();
			ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("error_locale"), null);
			jsfCtx.addMessage("messages", msgs);
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		} catch (Exception ex) {
			FacesContext jsfCtx = FacesContext.getCurrentInstance();
			ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("error_locale"), null);
			jsfCtx.addMessage("messages", msgs);
		}
	}
}
