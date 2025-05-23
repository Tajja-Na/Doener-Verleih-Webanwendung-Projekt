package de.hsrm.mi.web.projekt.entities.doener.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import de.hsrm.mi.web.projekt.doener.ui.DoenerFormular;
import de.hsrm.mi.web.projekt.doener.ui.ZutatFormular;
import de.hsrm.mi.web.projekt.entities.doener.Doener;
import de.hsrm.mi.web.projekt.entities.zutat.Zutat;

@Mapper(componentModel = "spring")
public interface DoenerMapper {
    
    DoenerFormular doenerToDoenerFormular(Doener doener);
    Doener doenerFormularToDoener(DoenerFormular doener);
    List<ZutatFormular> zutatToZutatFormList (List<Zutat> lstz);
}
