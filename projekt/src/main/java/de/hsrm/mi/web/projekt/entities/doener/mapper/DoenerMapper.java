package de.hsrm.mi.web.projekt.entities.doener.mapper;

import org.mapstruct.Mapper;

import de.hsrm.mi.web.projekt.doener.ui.DoenerFormular;
import de.hsrm.mi.web.projekt.entities.doener.Doener;

@Mapper(componentModel = "spring")
public interface DoenerMapper {
    
    DoenerFormular doenerToDoenerFormular(Doener doener);
    Doener doenerFormularToDoener(DoenerFormular doener);
}
