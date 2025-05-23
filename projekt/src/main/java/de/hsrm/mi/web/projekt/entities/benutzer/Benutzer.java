package de.hsrm.mi.web.projekt.entities.benutzer;

import de.hsrm.mi.web.projekt.benutzer.ui.BenutzerFormular;
import de.hsrm.mi.web.projekt.validators.GeeigneteLosung;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Benutzer {
    @Id
    private String loginName;

    @Version
    private long version;

    private String rolle = "";

    @NotNull    //eigentlich redundant, da der wert von grund auf 0 gesetzt ist
    private int vegetarizitaet = 0;

    @NotNull 
    @Size (min=3, max=60) 
    private String name = "";

    @NotNull 
    @Email 
    private String email = "";

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
        public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public long getVersion() {
        return version;
    }
    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
        result = prime * result + ((rolle == null) ? 0 : rolle.hashCode());
        result = prime * result + vegetarizitaet;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((losung == null) ? 0 : losung.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Benutzer other = (Benutzer) obj;
        if (loginName == null) {
            if (other.loginName != null)
                return false;
        } else if (!loginName.equals(other.loginName))
            return false;
        if (rolle == null) {
            if (other.rolle != null)
                return false;
        } else if (!rolle.equals(other.rolle))
            return false;
        if (vegetarizitaet != other.vegetarizitaet)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (losung == null) {
            if (other.losung != null)
                return false;
        } else if (!losung.equals(other.losung))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Benutzer [loginName=" + loginName + ", rolle=" + rolle + ", vegetarizitaet=" + vegetarizitaet
                + ", name=" + name + ", email=" + email + ", losung=" + losung + "]";
    }
}
