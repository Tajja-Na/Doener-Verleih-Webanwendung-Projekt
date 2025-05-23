package de.hsrm.mi.web.projekt.doener.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

import de.hsrm.mi.web.projekt.entities.zutat.Zutat;
import de.hsrm.mi.web.projekt.entities.zutat.ZutatRepository;

public class ZutatenServiceImpl implements ZutatenService{
    private ZutatRepository zr;
    private Logger logger = LoggerFactory.getLogger(ZutatenServiceImpl.class);

    @Override
    public List<Zutat> findAllZutaten() {
        return zr.findAll(Sort.by("name").ascending());
    }

}
