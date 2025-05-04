package de.hsrm.mi.web.projekt.benutzer.ui;

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

@Controller
@RequestMapping("/benutzer")
public class BenutzerController {
    Logger logger = LoggerFactory.getLogger(BenutzerController.class);

    @GetMapping("/{username}")
    public String bearbeiten_get(
        @PathVariable("username") String benutzer, 
        Model m){

        BenutzerFormular formular = new BenutzerFormular();
        m.addAttribute("username", benutzer);
        m.addAttribute("formular", formular);
        formular.setUsername(benutzer);
        return "benutzer/bearbeiten";
    }

    @PostMapping("/{username}")
    public String formular_post(
        @PathVariable("username") String benutzer,
        @ModelAttribute("formular") BenutzerFormular form,
        Model m){

        m.addAttribute("username", benutzer);
        m.addAttribute("formular", form);
        logger.info(form.toString());
        
        return "benutzer/bearbeiten";
    }
}