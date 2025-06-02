package de.hsrm.mi.web.projekt.benutzer.services;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.benutzer.ui.BenutzerException;
import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.benutzer.BenutzerRepository;

@Service
public class BenutzerServiceImpl implements BenutzerService{
    private BenutzerRepository br;
    private Logger logger = LoggerFactory.getLogger(BenutzerServiceImpl.class);

    @Autowired
    public BenutzerServiceImpl(BenutzerRepository br){
        this.br = br;
    }

    @Override
    public Benutzer saveBenutzer(Benutzer b) {
        return br.save(b);
    }

    @Override
    public Optional<Benutzer> findBenutzerById(String loginName) {
        return br.findById(loginName);
    }

    @Override
    public Collection<Benutzer> findAllBenutzer() {
        return br.findAll(Sort.by("loginName").ascending());
    }

    @Override
    public void deleteBenutzerById(String loginName) {
        br.deleteById(loginName);
    }

    @Override
    public Benutzer aktualisiereBenutzerAttribut(String loginName, String feldname, String wert) {
        Optional<Benutzer> benutzer = findBenutzerById(loginName);
        if(benutzer.isEmpty()){
            throw new BenutzerException("Benutzer konnte nicht gefunden werden");
        }

        switch(feldname) {
            case "name" : 
                benutzer.get().setName(wert);
                break;
            case "email" : 
                benutzer.get().setEmail(wert);
                logger.info("hallo bin im swtch case");
                break;  
            default:
                throw new IllegalArgumentException("Unbekanntes Feld: " + feldname); 
        };

        logger.info("hallo ich war mal in aktualisiere drinne");
        return benutzer.get();
    }
}
