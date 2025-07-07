package de.hsrm.mi.web.projekt.entities.doener;

import java.util.List;

import de.hsrm.mi.web.projekt.entities.zutat.ZutatDTO;

public record DoenerDTO(long id, String bezeichnung, int preis, int vegetarizitaet, int bestand, List<ZutatDTO> zutaten, int verfuegbar) {};
