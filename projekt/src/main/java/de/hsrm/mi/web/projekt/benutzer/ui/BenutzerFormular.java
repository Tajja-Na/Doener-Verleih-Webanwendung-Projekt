package de.hsrm.mi.web.projekt.benutzer.ui;

import de.hsrm.mi.web.projekt.validators.GeeigneteLosung;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BenutzerFormular {
    private String username = "";
    private String losungwh = "";

   
    public String getLosungwh() {
        return losungwh;
    }
    public void setLosungwh(String losungwh) {
        this.losungwh = losungwh;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "BenutzerFormular [username=" + username + "]";
    }
}