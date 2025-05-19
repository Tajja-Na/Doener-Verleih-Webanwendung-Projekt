package de.hsrm.mi.web.projekt.benutzer.ui;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BenutzerException extends RuntimeException{
    public BenutzerException(String message){
        super(message);
    }
}
