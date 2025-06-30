package de.hsrm.mi.web.projekt.doener.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.doener.Doener;
import de.hsrm.mi.web.projekt.entities.doener.DoenerRepository;
import de.hsrm.mi.web.projekt.entities.doener.mapper.DoenerMapperImpl;
import de.hsrm.mi.web.projekt.entities.zutat.Zutat;
import de.hsrm.mi.web.projekt.entities.zutat.ZutatRepository;

@Service
public class DoenerErfindungsService {
    @Autowired
    private ZutatenService zs;
    @Autowired
    private DoenerService ds;

    @Autowired
    private DoenerNamingService dns;

    private Random random = new Random();

    public Doener neuenDoenerErfinden(){
        //Zutaten random auswählen
        //mind. 1 Zutat und max. 25% der verfügbaren Zutaten
        List<Zutat> alleZutaten = zs.findAllZutaten();
        int maxZutat = Math.max(1, alleZutaten.size() / 4);   // durch 4 für 25% aller Zutaten - es soll trotzdem mind 1 sein
        int minZutat = 1;

        int zutatenAuswahl = random.nextInt(maxZutat - minZutat + 1) + minZutat;
        Collections.shuffle(alleZutaten);
        List<Zutat> auswahlZutat = new ArrayList<>();

        for(int i = 0; i < zutatenAuswahl; i++){
            auswahlZutat.add(alleZutaten.get(i));
        }

        //Vegezitarität aus Zutaten berechnen
        int minVWert = auswahlZutat.get(0).getVegetarizitaet();  //einfach den ersten wert aus der zutaten liste nehmen, um ein richtigen einstiegswert zu haben
                
        for(Zutat z : auswahlZutat){
            int aktuellerVWert = z.getVegetarizitaet();

            if(aktuellerVWert < minVWert){
                minVWert = aktuellerVWert;
            }
        }

        //Preis random aus dem Bereich 5-29
        int preis = random.nextInt(25) +5;

        //Bestand random aus dem Wertebreich 1-100
        int bestand = random.nextInt(100) + 1;

        //Name aus Nameservice erzeugen
        String name = dns.getName();

        Doener neuerDoener = new Doener();
        neuerDoener.setZutaten(auswahlZutat);
        neuerDoener.setVegetarizitaet(minVWert);
        neuerDoener.setPreis(preis);
        neuerDoener.setBestand(bestand);
        neuerDoener.setBezeichnung(name);

        return ds.saveDoener(neuerDoener);
    }
}
