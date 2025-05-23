package de.hsrm.mi.web.projekt.entities.zutat;

import java.util.List;

import de.hsrm.mi.web.projekt.entities.doener.Doener;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Zutat {
    @Id
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
}
