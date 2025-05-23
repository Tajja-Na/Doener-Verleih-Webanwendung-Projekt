package de.hsrm.mi.web.projekt.entities.doener;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import de.hsrm.mi.web.projekt.entities.zutat.Zutat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Doener {

    @Id 
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @NotNull
    @NotBlank
    @Size (min=3, max=40) 
    private String bezeichnung;

    @Min(value=0)
    private int preis;

    @NotNull //eigentlich redundant, da der wert von grund auf 0 gesetzt ist
    private int vegetarizitaet = 0; //oder maybe muss es bei doener und zutat nicht 0 gesetzt werden

    @Min(value=0)
    private int bestand;

    @ManyToMany
    private List<Zutat> zutaten;


    public Doener(){
        //Liste initialisieren
        
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public long getVersion() {
        return version;
    }


    public void setVersion(long version) {
        this.version = version;
    }


    public String getBezeichnung() {
        return bezeichnung;
    }


    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }


    public int getPreis() {
        return preis;
    }


    public void setPreis(int preis) {
        this.preis = preis;
    }


    public int getVegetarizitaet() {
        return vegetarizitaet;
    }


    public void setVegetarizitaet(int vegetarizitaet) {
        this.vegetarizitaet = vegetarizitaet;
    }


    public int getBestand() {
        return bestand;
    }


    public void setBestand(int bestand) {
        this.bestand = bestand;
    }


    public List<Zutat> getZutaten() {
        return zutaten;
    }


    public void setZutaten(List<Zutat> zutaten) {
        this.zutaten = zutaten;
    }
}
