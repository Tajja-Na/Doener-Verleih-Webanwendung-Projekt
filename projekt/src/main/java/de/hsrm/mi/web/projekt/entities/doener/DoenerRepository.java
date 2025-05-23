package de.hsrm.mi.web.projekt.entities.doener;

import org.springframework.data.jpa.repository.JpaRepository;

//@Interface wäre hier falsch, das würde eine Annotation definieren, so wie in GeeigneteLosung 
public interface DoenerRepository extends JpaRepository<Doener, Long>{
    //Long, weil der Primärschlüssel die id vom Doener ist 
}
