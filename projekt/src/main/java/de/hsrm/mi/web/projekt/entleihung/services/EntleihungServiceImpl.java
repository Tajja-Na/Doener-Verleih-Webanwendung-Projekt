package de.hsrm.mi.web.projekt.entleihung.services;

import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.benutzer.services.BenutzerService;
import de.hsrm.mi.web.projekt.doener.services.DoenerService;
import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.doener.Doener;
import de.hsrm.mi.web.projekt.entleihung.EntleihException;
import de.hsrm.mi.web.projekt.messaging.FrontendNachrichtEvent;
import de.hsrm.mi.web.projekt.messaging.NachrichtenTyp;
import de.hsrm.mi.web.projekt.messaging.Operation;
import jakarta.transaction.Transactional;

@Service
public class EntleihungServiceImpl implements EntleihungService {
    private BenutzerService bs;
    private DoenerService ds;
    private ApplicationEventPublisher publisher;

    public EntleihungServiceImpl(BenutzerService bs, DoenerService ds, ApplicationEventPublisher publisher) {
        this.bs = bs;
        this.ds = ds;
        this.publisher = publisher;
    }

    @Transactional
    @Override
    public void entleiheDoener(long doenerId, String loginName) {
        Benutzer benutzer = bs.findBenutzerById(loginName).orElseThrow(() -> 
            new EntleihException("Der Benutzer mit dem Login-Namen: " + loginName + " ist nicht in der DB vorhanden"));

        Doener doener = ds.findDoenerById(doenerId).orElseThrow(() -> 
            new EntleihException("Der Döner mit der ID: " + doenerId + " ist nicht in der DB vorhanden"));

        if(doener.getVerfuegbar() > 0) {
            benutzer.getEntlieheneDoener().add(doener);
            doener.setEntliehen(doener.getEntliehen()+1);
        }else{
            throw new EntleihException("Der Döner mit der ID: " + doenerId + " ist nicht mehr verfügbar, Verfügbarkeit: " + doener.getVerfuegbar());
        }
        publisher.publishEvent(new FrontendNachrichtEvent(NachrichtenTyp.DOENER, doenerId, Operation.BOOKED));
    }

    @Transactional
    @Override
    public void zurueckgebeDoener(long doenerId, String loginName) {
        Benutzer benutzer = bs.findBenutzerById(loginName).orElseThrow(() -> 
            new EntleihException("Der Benutzer mit dem Login-Namen: " + loginName + " ist nicht in der DB vorhanden"));

        Doener doener = ds.findDoenerById(doenerId).orElseThrow(() -> 
            new EntleihException("Der Döner mit der ID: " + doenerId + " ist nicht in der DB vorhanden"));
    
        if(benutzer.getEntlieheneDoener().contains(doener)) {
            benutzer.getEntlieheneDoener().remove(doener);
            doener.setEntliehen(doener.getEntliehen()-1);
        }else{
            throw new EntleihException("Der Döner mit der ID: " + doenerId + " ist nicht in der EntliehenDöner Liste des Benutzers: " + loginName + " verzeichnet");
        }
        publisher.publishEvent(new FrontendNachrichtEvent(NachrichtenTyp.DOENER, doenerId, Operation.RETURNED));
    }

    @Override
    public List<Doener> findeEntleihungenVonBenutzer(String loginName) {
        Benutzer benutzer = bs.findBenutzerById(loginName).orElseThrow(() -> 
            new EntleihException("Der Benutzer mit dem Login-Namen: " + loginName + " ist nicht in der DB vorhanden"));

        return benutzer.getEntlieheneDoener();
    }
}
