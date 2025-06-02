package de.hsrm.mi.web.projekt.benutzer.services;

import java.util.Collection;
import java.util.Optional;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;

public interface BenutzerService {

    Benutzer saveBenutzer(Benutzer benutzer);
    Optional<Benutzer> findBenutzerById(String loginName);
    Collection<Benutzer> findAllBenutzer();
    void deleteBenutzerById(String loginName);

    Benutzer aktualisiereBenutzerAttribut(String loginName, String feldname, String wert);  //Als feldname kommen in unserem Fall nur die Strings name oder email infrage
}
