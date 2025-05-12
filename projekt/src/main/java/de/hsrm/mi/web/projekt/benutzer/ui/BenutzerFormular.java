package de.hsrm.mi.web.projekt.benutzer.ui;

import de.hsrm.mi.web.projekt.validators.GeeigneteLosung;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BenutzerFormular {
    private int vegetarizitaet = 0;
    private String username = "";

    @NotNull 
    @Size (min=3, max=60) 
    private String name = "";

    @Email 
    private String email = "";
    private String rolle = "";
    
    @GeeigneteLosung (message="{benutzer.fehler.geeignetelosung}")
    private String losung = "";

    private String losungwh = "";

    public int getVegetarizitaet() {
        return vegetarizitaet;
    }
    public void setVegetarizitaet(int vegetarizitaet) {
        this.vegetarizitaet = vegetarizitaet;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRolle() {
        return rolle;
    }
    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
    public String getLosung() {
        return losung;
    }
    public void setLosung(String losung) {
        this.losung = losung;
    }
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
        return "BenutzerFormular [vegetarizitaet=" + vegetarizitaet + ", username=" + username + ", name=" + name
                + ", email=" + email + ", rolle=" + rolle + ", losung=" + losung + "]";
    }
}