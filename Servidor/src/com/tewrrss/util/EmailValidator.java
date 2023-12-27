package com.tewrrss.util;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	private static final Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public EmailValidator() { /* Se necesita un constructor, aunque esté vacío */ }

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return; // Deja la validaci�n para el atributo 'required'
		}

		String email = value.toString();
		Matcher matcher = pattern.matcher(email);

		// Si el email no esta bien formado, devolvemos una excepción de validación, lo
		// que causa un mensaje en el Front
		if (!matcher.matches()) {
			FacesContext jsfCtx = FacesContext.getCurrentInstance();
			ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

			FacesMessage message = new FacesMessage(bundle.getString("session_register_invalid_email"));
			throw new ValidatorException(message);
		}
	}
}
