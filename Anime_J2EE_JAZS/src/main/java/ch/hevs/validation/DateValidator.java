package ch.hevs.validation;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="dateValidator")
public class DateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UIInput sd = (UIInput)component.getAttributes().get("firstDate");
        Date firstDate = (Date)sd.getValue();
        Date secondDate = (Date)value;
        if(secondDate != null){
        	if(!firstDate.before(secondDate)){
                FacesMessage message = new FacesMessage("Entered dates are invalid: first date must be before second date");
                FacesContext.getCurrentInstance().addMessage(null, message);
                throw new ValidatorException(message);
            }
        }
        
    }

}