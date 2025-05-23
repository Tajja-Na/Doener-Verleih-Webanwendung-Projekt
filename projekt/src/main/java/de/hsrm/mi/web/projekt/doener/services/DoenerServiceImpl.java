package de.hsrm.mi.web.projekt.doener.services;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.doener.Doener;
import de.hsrm.mi.web.projekt.entities.doener.DoenerRepository;

@Service
public class DoenerServiceImpl implements DoenerService{
    private DoenerRepository dr;
    private Logger logger = LoggerFactory.getLogger(DoenerServiceImpl.class);

    @Autowired
    public DoenerServiceImpl(DoenerRepository dr){
        this.dr = dr;
    }

    @Override
    public Doener saveDoener(Doener doener) {
        return dr.save(doener);
    }

    @Override
    public Optional<Doener> findDoenerById(long id) {
        return dr.findById(id);
    }

    @Override
    public Collection<Doener> findAllDoener() {
       return dr.findAll(Sort.by("id").ascending());
    }

    @Override
    public void deleteDoenerById(long id) {
        dr.deleteById(id);
    }
}
