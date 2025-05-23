package de.hsrm.mi.web.projekt.doener.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.zutat.Zutat;
import de.hsrm.mi.web.projekt.entities.zutat.ZutatRepository;

@Service
public class ZutatenServiceImpl implements ZutatenService{
    private ZutatRepository zr;
    private Logger logger = LoggerFactory.getLogger(ZutatenServiceImpl.class);

    @Autowired
    public ZutatenServiceImpl(ZutatRepository zr){
        this.zr = zr;
    }

    @Override
    public List<Zutat> findAllZutaten() {
        return zr.findAll(Sort.by("name").ascending());
    }

}
