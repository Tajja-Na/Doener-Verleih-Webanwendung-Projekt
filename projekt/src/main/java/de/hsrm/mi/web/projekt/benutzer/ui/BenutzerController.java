package de.hsrm.mi.web.projekt.benutzer.ui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.projekt.benutzer.services.BenutzerService;
import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.benutzer.mapper.BenutzerMapper;
import de.hsrm.mi.web.projekt.entities.doener.Doener;
import jakarta.persistence.OptimisticLockException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
// @SessionAttributes("formularMap")
@SessionAttributes("formular")
@RequestMapping("/benutzer")
public class BenutzerController {
    private Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    private BenutzerService bs;
    private BenutzerMapper mapper;

    @Autowired
    public BenutzerController(BenutzerService bs, BenutzerMapper mapper) {
        this.bs = bs;
        this.mapper = mapper;
    }

    @ModelAttribute("formular")
    public BenutzerFormular initFormular() {
        return new BenutzerFormular();
    }

    /*
     * @ModelAttribute("formularMap")
     * public Map<String, BenutzerFormular> initFormularMap() {
     * Map<String, BenutzerFormular> neueMap = new HashMap<String,
     * BenutzerFormular>();
     * return neueMap;
     * }
     * 
     * @GetMapping("/{username}")
     * public String bearbeiten_get(
     * 
     * @PathVariable("username") String benutzer,
     * 
     * @ModelAttribute("formularMap") Map<String, BenutzerFormular> formularMap,
     * Locale locale,
     * Model m) {
     * 
     * BenutzerFormular formular;
     * 
     * if(!formularMap.containsKey(benutzer)){
     * formular = new BenutzerFormular();
     * formular.setUsername(benutzer);
     * formularMap.put(benutzer, formular);
     * }else{
     * formular = formularMap.get(benutzer);
     * }
     * 
     * m.addAttribute("locale", locale);
     * m.addAttribute("sprache", locale.getDisplayLanguage());
     * m.addAttribute("username", benutzer);
     * m.addAttribute("formular", formular);
     * return "benutzer/bearbeiten";
     * }
     * 
     * @PostMapping("/{username}")
     * public String formular_post(
     * 
     * @PathVariable("username") String benutzer,
     * 
     * @Valid @ModelAttribute("formular") BenutzerFormular form,
     * BindingResult result,
     * 
     * @ModelAttribute("formularMap") Map<String, BenutzerFormular> formularMap,
     * Model m) {
     * 
     * if(!form.getLosung().equals(form.getLosungwh())){
     * result.rejectValue("losungwh", "benutzer.fehler.losungwiederholung",
     * "Losungen weichen ab");
     * }
     * 
     * if(result.hasErrors()){
     * return "benutzer/bearbeiten";
     * }else{
     * formularMap.put(benutzer, form);
     * 
     * m.addAttribute("username", benutzer);
     * m.addAttribute("formular", form);
     * logger.info(form.toString());
     * }
     * 
     * return "benutzer/bearbeiten";
     * }
     */

    @GetMapping("/{loginName}")
    public String bearbeiten_get(
            @PathVariable("loginName") String benutzer,
            @ModelAttribute("formular") BenutzerFormular form,
            Locale locale,
            Model m) {
        Optional<Benutzer> b = bs.findBenutzerById(benutzer);

        if (b.isEmpty()) {
            form = new BenutzerFormular();
            form.setLoginName(benutzer);
        } else {
            form = mapper.benutzerToBenutzerFormular(b.get());
            form.setVersion(b.get().getVersion());
        }

        m.addAttribute("locale", locale);
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("loginName", benutzer);
        m.addAttribute("formular", form);
        return "benutzer/bearbeiten";
    }

    @PostMapping("/{loginName}")
    public String formular_post(
            @PathVariable("loginName") String benutzer,
            @Valid @ModelAttribute("formular") BenutzerFormular form,
            BindingResult result,
            Model m) {

        Optional<Benutzer> b = bs.findBenutzerById(benutzer);

        if (!form.getLosung().equals(form.getLosungwh())) {
            result.rejectValue("losungwh", "benutzer.fehler.losungwiederholung", "Losungen weichen ab");
        }

        try {
            if (!result.hasErrors()) {
                if (form.getLosung().equals("")) {
                    if (b.isEmpty()) {
                        throw new BenutzerException("Es fehlt eine Losung!");
                    } else {
                        form.setLosung(b.get().getLosung());
                    }
                }

                Benutzer neuerBenutzer = mapper.benutzerFormularToBenutzer(form);
                neuerBenutzer.setLoginName(benutzer);

                //hier muss nochmal geprüft werden ob der benutzer bereits in der db ist oder nicht, wenn er ncoh nciht drinne is 
                //is b empty, wodurch eine fehlermeldung beim speichern eines neuen benutzers entsteht
                if (b.isPresent()) {  
                    long alteVersion = form.getVersion();
                    long aktuelleVersion = neuerBenutzer.getVersion();

                    if (alteVersion == aktuelleVersion) {
                        bs.saveBenutzer(neuerBenutzer);
                    } else {
                        throw new OptimisticLockException();
                    }
                }else{
                    bs.saveBenutzer(neuerBenutzer);
                }

                logger.info(form.toString());
            }
        } catch (BenutzerException e) {
            m.addAttribute("info", e.getMessage());
            m.addAttribute("loginName", benutzer);
            m.addAttribute("formular", form);
            return "/benutzer/bearbeiten";
        } catch (Exception e) {
            m.addAttribute("info", e.getMessage());
            m.addAttribute("loginName", benutzer);
            m.addAttribute("formular", form);
            return "/benutzer/bearbeiten";
        }

        m.addAttribute("loginName", benutzer);
        m.addAttribute("formular", form);
        return "redirect:/benutzer";
    }

    @GetMapping
    public String liste_get(Model m) {
        Collection<Benutzer> alleBenutzer = bs.findAllBenutzer();
        logger.info("Anzahl Benutzer: {}", alleBenutzer.size());

        m.addAttribute("benutzer", alleBenutzer);
        return "benutzer/liste";
    }

    @GetMapping("/{loginName}/delete")
    public String delete_get(
            @PathVariable("loginName") String benutzer,
            Model m) {
        bs.deleteBenutzerById(benutzer);
        return "redirect:/benutzer";
    }

    @GetMapping("/{loginName}/hx/feld/{feldname}")
    public String htmx_bearbeiten(
            @PathVariable ("feldname") String feldname, 
            @PathVariable ("loginName") String loginName,
            Model m) {
        Optional<Benutzer> benutzer = bs.findBenutzerById(loginName);

        String wert = "";
        if(benutzer.isPresent()){
            wert = 
                switch(feldname) {
                    case "name" -> benutzer.get().getName();
                    case "email" -> benutzer.get().getEmail();
                    default -> "";
                };
        }

        m.addAttribute("loginName", loginName);
        m.addAttribute("feldname", feldname);
        m.addAttribute("wert", wert);

        return "benutzer/eingabefeld :: bearbeiten";
    }
    
    @PutMapping("/{loginName}/hx/feld/{feldname}")
    public String htmx_post(
            @PathVariable ("loginName") String loginName,
            @PathVariable ("feldname") String feldname,
            @RequestParam("wert") String wert,
            Model m) {

        logger.info("feldname: " +feldname);

        Optional<Benutzer> benutzer = bs.findBenutzerById(loginName);
        String alterWert = "";

        try{
            Benutzer b = bs.aktualisiereBenutzerAttribut(loginName, feldname, wert);

            bs.saveBenutzer(b);

            m.addAttribute("wert", wert);
            m.addAttribute("loginName", loginName);
            m.addAttribute("feldname", feldname);

            return "benutzer/eingabefeld :: ausgeben";

        }catch(Exception e){

            if(benutzer.isEmpty()){
                throw new BenutzerException("Benutzer: "+loginName+" wurde nicht gefunden");
            }
            switch (feldname) {
                case "name": 
                    alterWert = benutzer.get().getName();
                    break;
                case "email": 
                    alterWert = benutzer.get().getEmail();
                    break;
                };
            logger.info("bin hier im catch drinne - hilfe");

            m.addAttribute("wert", alterWert);
            m.addAttribute("loginName", loginName);
            m.addAttribute("feldname", feldname);

            m.addAttribute("errorMessage" , "benutzer.fehler.fehler");  //switch einfügen um zu gucken was für ein fehler
            
            return "benutzer/eingabefeld :: bearbeiten";
        }
    }
}