package de.hsrm.mi.web.projekt.benutzer.ui;

public class BenutzerFormular {
    private int vegetarizitaet = 0;
    private String name = "";
    private String email = "";
    private String rolle = "";
    private String passwort = "";

    public int getVegetarizitaet() {
        return vegetarizitaet;
    }
    public void setVegetarizität(int vegetarizitaet) {
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
    public String getPasswort() {
        return passwort;
    }
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

}
