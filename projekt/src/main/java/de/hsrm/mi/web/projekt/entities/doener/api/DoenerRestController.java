package de.hsrm.mi.web.projekt.entities.doener.api;

import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.projekt.doener.services.DoenerService;
import de.hsrm.mi.web.projekt.entities.doener.Doener;
import de.hsrm.mi.web.projekt.entities.doener.DoenerDTO;
import de.hsrm.mi.web.projekt.entities.doener.mapper.DoenerMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api")
public class DoenerRestController {
    private DoenerService ds;
    private DoenerMapper mapper;

    public DoenerRestController(DoenerService ds, DoenerMapper mapper){
        this.ds = ds;
        this.mapper = mapper;
    }
    
    @GetMapping("/doener/{id}")
    public DoenerDTO get_id(@PathVariable ("id") long id) {
        Optional<Doener> doener = ds.findDoenerById(id);

        if(doener.isEmpty()){
            throw new DoenerNotFoundException("Döner mit der ID: " +id+ " konnte nicht gefunden werden");
        }else{
            return mapper.doenerToDoenerDTO(doener.get());
        }
    }

    @GetMapping("/doener")
    public List<DoenerDTO> get_Alledoener() {   //das ist nh list(doenerDTO)
        List<Doener> alleDoener = (List<Doener>) ds.findAllDoener();    //müssen das erstmal casten, weil findAllDoener gibt keine List sondern eine Collection zurück
        return mapper.doenerListToDoenerDTOList(alleDoener);            //Collection ist ein allgemeiner Begriff für eine Sammlung von Objekten - List eine spezifische Art von Collection 
    } 
}
