package de.hsrm.mi.web.projekt.entities.benutzer;

import org.springframework.data.jpa.repository.JpaRepository;

//@Interface wäre hier falsch, das würde eine Annotation definieren, so wie in GeeigneteLosung 
public interface BenutzerRepository extends JpaRepository<Benutzer, String>{  
    //String, weil der Primärschlüssel der loginname und somit ein String ist
}
