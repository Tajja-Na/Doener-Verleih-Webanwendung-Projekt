package de.hsrm.mi.web.projekt.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GeeigneteLosungValidator implements ConstraintValidator<GeeigneteLosung, String>{

    @Override
    public boolean isValid(String obj, ConstraintValidatorContext ctx){
        
        if(obj == null || obj.isEmpty()){
            return true;
        }

        if(obj.contains("42") || obj.toLowerCase().contains("zweiundvierzig")){
            return false;
        }
        return true;
    }
}
