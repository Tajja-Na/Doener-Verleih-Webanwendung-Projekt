package de.hsrm.mi.web.projekt.entleihung.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.projekt.entities.doener.Doener;
import de.hsrm.mi.web.projekt.entities.doener.DoenerDTO;
import de.hsrm.mi.web.projekt.entities.doener.mapper.DoenerMapper;
import de.hsrm.mi.web.projekt.entleihung.EntleihungCreateDTO;
import de.hsrm.mi.web.projekt.entleihung.services.EntleihungService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class EntleihungRestController {
    private EntleihungService es;
    private DoenerMapper mapper;

    public EntleihungRestController(EntleihungService es, DoenerMapper mapper){
        this.es = es;
        this.mapper = mapper;
    }

    @PostMapping("/entleihung")
    public void doenerEntleihen(@RequestBody EntleihungCreateDTO eDto) {
        es.entleiheDoener(eDto.doenerId(), eDto.loginName());
    }

    @DeleteMapping("/{loginName}/{Id}")
    public void doenerRueckgabe(@PathVariable String loginName, @PathVariable long Id) {
        es.zurueckgebeDoener(Id, loginName);
    }

    @GetMapping("/{loginName}")
    public List<DoenerDTO> get_entlieheneDoener(@PathVariable String loginName) {
        List<Doener> entlieheneDoener = es.findeEntleihungenVonBenutzer(loginName);
        List<DoenerDTO> dtoListe = new ArrayList<>();

        for(Doener doener: entlieheneDoener){
            dtoListe.add(mapper.doenerToDoenerDTO(doener));
        }
        return dtoListe;
    }
}
