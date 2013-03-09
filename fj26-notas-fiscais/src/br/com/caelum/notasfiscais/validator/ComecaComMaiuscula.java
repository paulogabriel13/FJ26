package br.com.caelum.notasfiscais.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ccm")
public class ComecaComMaiuscula implements Validator {

	public void validate(FacesContext fc, UIComponent component, Object object) throws ValidatorException {
		String valor = object.toString();
		if (!valor.matches("[A-Z].*")) {
			throw new ValidatorException(new FacesMessage("Deveria começar com maiúscula"));
		}
	}

}
