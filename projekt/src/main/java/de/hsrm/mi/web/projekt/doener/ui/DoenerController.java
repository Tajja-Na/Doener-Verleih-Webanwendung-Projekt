package de.hsrm.mi.web.projekt.doener.ui;

import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.projekt.doener.services.DoenerService;
import de.hsrm.mi.web.projekt.entities.doener.Doener;
import de.hsrm.mi.web.projekt.entities.doener.mapper.DoenerMapper;
import jakarta.persistence.OptimisticLockException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@SessionAttributes("dformular")
@RequestMapping("/doener")
public class DoenerController {
    private Logger logger = LoggerFactory.getLogger(DoenerController.class);
    private DoenerService ds;
    private DoenerMapper mapper;

    @Autowired
    public DoenerController(DoenerService ds, DoenerMapper mapper) {
        this.ds = ds;
        this.mapper = mapper;
    }

    @ModelAttribute("dformular")
    public DoenerFormular initformular() {
        return new DoenerFormular();
    }

    @GetMapping("/{id}")
    public String bearbeiten_get(
            @PathVariable("id") long id,
            @ModelAttribute("dformular") DoenerFormular form,
            Locale locale,
            Model m) { 
        
        if(id == 0){  //sollte die id 0 sein, wird damit signalisiert das ein neuer Döner angelegt werden soll
            form = new DoenerFormular();
            //form.setId(id);  <- das nicht weil die id wird automatisch generiert
        }else{
            Optional<Doener> doener = ds.findDoenerById(id);
            form = mapper.doenerToDoenerFormular(doener.get());
            form.setVersion(doener.get().getVersion());
        }

        m.addAttribute("locale", locale);
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("id", id);
        m.addAttribute("dformular", form);
        return "doener/bearbeiten";
    }
    
    @PostMapping("/{id}")
    public String postMethodName(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("dformular") DoenerFormular form,
            BindingResult result,
            Model m) {

        Optional<Doener> doener = ds.findDoenerById(id);

         try{
            if(!result.hasErrors()){
                /* if(form.getBezeichnung().equals("")){
                    if(doener.isEmpty()){
                        throw new DoenerException("Es fehlt eine Bezeichnung!");
                    }else{
                        form.setBezeichnung(doener.get().getBezeichnung());
                    }
                } ich glaub das macht nicht mal Sinn, weil bezeichnung die annotation notblank hat, bei einem fehler geht er eh nicht hier rein
                maybe einfach ins else packen? aber dann sind da 2 fehlermeldungen, is ja auch schwachsinn*/

                Doener neuerDoener = mapper.doenerFormularToDoener(form);
                neuerDoener.setId(id);

                if(doener.isPresent()){
                    long alteVersion = form.getVersion();
                    long aktuelleVersion = neuerDoener.getVersion();

                    if(alteVersion == aktuelleVersion){
                        ds.saveDoener(neuerDoener);
                    }else{
                        throw new OptimisticLockException();
                    }
                }else{
                    ds.saveDoener(neuerDoener);
                }

                logger.info(form.toString());
            }else{
                return "doener/bearbeiten";
            }

        }catch(DoenerException e){
            m.addAttribute("info", e.getMessage());
            m.addAttribute("id", id);
            m.addAttribute("dformular", form);
            return "doener/bearbeiten";
        }catch(Exception e){
            m.addAttribute("info", e.getMessage());
            m.addAttribute("id", id);
            m.addAttribute("dformular", form);
            return "doener/bearbeiten";
        }

        m.addAttribute("id", id);
        m.addAttribute("dformular", form);
        return "redirect:/doener";
    }

    @GetMapping
    public String liste_get(Model m) {
        Collection<Doener> alleDoener = ds.findAllDoener();
        logger.info("Anzahl Doener: {}", alleDoener.size());
        m.addAttribute("doener", alleDoener);
        return "doener/liste";
    } 
    
    @GetMapping("/{id}/delete")
    public String delete_get(
            @PathVariable("id") long id,
            Model m) {
        ds.deleteDoenerById(id);
        return "redirect:/doener";
    }
}
