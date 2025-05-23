package de.hsrm.mi.web.projekt.entities.benutzer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import de.hsrm.mi.web.projekt.benutzer.ui.BenutzerFormular;
import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;

@Mapper(componentModel = "spring")
public interface BenutzerMapper {

    //da es losungwh nur im Formular gibt muss es hier beim mappen ignoriert werden, weil der mapper sonst keinen passenden partner beim benutzer findet
    @Mapping(target = "losungwh", ignore = true)  
    BenutzerFormular benutzerToBenutzerFormular(Benutzer benutzer);

    Benutzer benutzerFormularToBenutzer(BenutzerFormular formular);
}
