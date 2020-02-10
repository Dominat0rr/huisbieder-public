package com.domain.fednot_demo_huisbieder.services;

import com.domain.fednot_demo_huisbieder.entities.Gemeente;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface GemeenteService {
    void create(Gemeente gemeente);
    List<Gemeente> findAll();
    Optional<Gemeente> findById(long id);
    Optional<Gemeente> findByPostcode(String postcode);
    Optional<Gemeente> findByNaam(String naam);
    List<Gemeente> findAllByNaam(String naam);
    List<Gemeente> findAllByNaamContaining(String str);
}
