package de.hsrm.mi.web.projekt.benutzer.ui;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("formularMap")
@RequestMapping("/benutzer")
public class BenutzerController {
    Logger logger = LoggerFactory.getLogger(BenutzerController.class);

    @ModelAttribute("formularMap")
    public Map<String, BenutzerFormular> initFormularMap() {
        Map<String, BenutzerFormular> neueMap = new HashMap<String, BenutzerFormular>();
        return neueMap;
    }

    @GetMapping("/{username}")
    public String bearbeiten_get(
        @PathVariable("username") String benutzer,
        @ModelAttribute("formularMap") Map<String, BenutzerFormular> formularMap,
        Model m) {
        
        BenutzerFormular formular;

        if(!formularMap.containsKey(benutzer)){
            formular = new BenutzerFormular();
            formular.setUsername(benutzer);
            formularMap.put(benutzer, formular);
        }else{
            formular = formularMap.get(benutzer);
        }

        m.addAttribute("username", benutzer);
        m.addAttribute("formular", formular);
        return "benutzer/bearbeiten";
    }

    @PostMapping("/{username}")
    public String formular_post(
        @PathVariable("username") String benutzer,
        @ModelAttribute("formular") BenutzerFormular form,
        @ModelAttribute("formularMap") Map<String, BenutzerFormular> formularMap,
        Model m) {
        
        formularMap.put(benutzer, form);

        m.addAttribute("username", benutzer);
        m.addAttribute("formular", form);
        logger.info(form.toString());

        return "benutzer/bearbeiten";
    }
}