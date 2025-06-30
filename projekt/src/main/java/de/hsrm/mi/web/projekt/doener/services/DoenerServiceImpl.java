package de.hsrm.mi.web.projekt.doener.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.doener.Doener;
import de.hsrm.mi.web.projekt.entities.doener.DoenerRepository;
import de.hsrm.mi.web.projekt.entities.zutat.Zutat;
import de.hsrm.mi.web.projekt.entities.zutat.ZutatRepository;
import de.hsrm.mi.web.projekt.messaging.FrontendNachrichtEvent;
import de.hsrm.mi.web.projekt.messaging.FrontendNachrichtService;
import de.hsrm.mi.web.projekt.messaging.FrontendNachrichtServiceImpl;
import de.hsrm.mi.web.projekt.messaging.NachrichtenTyp;
import de.hsrm.mi.web.projekt.messaging.Operation;

@Service
public class DoenerServiceImpl implements DoenerService{
    private DoenerRepository dr;
    private ZutatRepository zr;
    private Logger logger = LoggerFactory.getLogger(DoenerServiceImpl.class);
    private FrontendNachrichtService fs;

    @Autowired
    public DoenerServiceImpl(DoenerRepository dr, ZutatRepository zr, FrontendNachrichtService fs){
        this.dr = dr;
        this.zr = zr;
        this.fs = fs;
    }

    @Override
    public Doener saveDoener(Doener doener) {
        List<Zutat> zutaten = doener.getZutaten();
        
        if(!zutaten.isEmpty()){
            int minVWert = zutaten.get(0).getVegetarizitaet();  //einfach den ersten wert aus der zutaten liste nehmen, um ein richtigen einstiegswert zu haben
                
            for(Zutat z : zutaten){
                int aktuellerVWert = z.getVegetarizitaet();

                if(aktuellerVWert < minVWert){
                    minVWert = aktuellerVWert;
                }
            }
            doener.setVegetarizitaet(minVWert);
        }

        boolean istneu = (!dr.existsById(doener.getId()));

        /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } */

        Doener neuerDoener = dr.save(doener);
        FrontendNachrichtEvent nachricht = new FrontendNachrichtEvent(
            NachrichtenTyp.DOENER, neuerDoener.getId(), istneu ? Operation.CREATE : Operation.UPDATE);
        fs.sendEvent(nachricht);
        logger.info("die nachricht: {} {} {}", nachricht.getOperation(), nachricht.getId(), nachricht.getTyp());
        return neuerDoener;
    }

    @Override
    public Optional<Doener> findDoenerById(long id) {
        return dr.findById(id);
    }

    @Override
    public Collection<Doener> findAllDoener() {
       return dr.findAll(Sort.by("id").ascending());
    }

    @Override
    public void deleteDoenerById(long id) {
        dr.deleteById(id);

        fs.sendEvent(new FrontendNachrichtEvent(
            NachrichtenTyp.DOENER, id, Operation.DELETE
        ));
    }
}
