package de.hsrm.mi.web.projekt.benutzer.ui;

import de.hsrm.mi.web.projekt.validators.GeeigneteLosung;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BenutzerFormular {
    private String loginName = "";
    private String losungwh = "";
    
    private String rolle = "";
    private int vegetarizitaet = 0;

    @NotNull 
    @Size (min=3, max=60) 
    private String name = "";

    @NotNull 
    @Email 
    private String email;

    @NotNull 
    @GeeigneteLosung (message="{benutzer.fehler.geeignetelosung}")
    private String losung = "";

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
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    @Override
    public String toString() {
        return "BenutzerFormular [loginName=" + loginName + ", losungwh=" + losungwh + ", rolle=" + rolle
                + ", vegetarizitaet=" + vegetarizitaet + ", name=" + name + ", email=" + email + ", losung=" + losung
                + "]";
    }
}