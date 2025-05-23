package de.hsrm.mi.web.projekt.doener.ui;

import java.util.List;

import de.hsrm.mi.web.projekt.entities.doener.Doener;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ZutatFormular {
    @Size(min=13 , max= 13)
    private String ean; //Europ. Artikel Nummer

    @Version
    private long version;

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    private int vegetarizitaet;

    @ManyToMany
    private List<Doener> alleDoener;

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVegetarizitaet() {
        return vegetarizitaet;
    }

    public void setVegetarizitaet(int vegetarizitaet) {
        this.vegetarizitaet = vegetarizitaet;
    }

    public List<Doener> getAlleDoener() {
        return alleDoener;
    }

    public void setAlleDoener(List<Doener> alleDoener) {
        this.alleDoener = alleDoener;
    }
}
