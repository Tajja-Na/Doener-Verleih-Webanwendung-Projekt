package de.hsrm.mi.web.projekt.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Min;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME) 
@Constraint(validatedBy = GeeigneteLosungValidator.class)
@Documented 
public @interface GeeigneteLosung {
    String message() default "{benutzer.fehler.geeignetelosung}";
    Class<? extends Payload>[] payload() default {};
    Class<?>[] groups() default {}; 
}
