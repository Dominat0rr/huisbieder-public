package com.domain.fednot_demo_huisbieder.services;

import com.domain.fednot_demo_huisbieder.entities.Pand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface PandService {
    void create(Pand pand, String pandURL);
    Optional<Pand> findById(long id);
    List<Pand> findAll();
    Page<Pand> findAll(Pageable pageable);
    List<Pand> findBAllByGebruikerId(Long id);
    List<Pand> findAllByGemeenteNaam(String naam);
    List<Pand> findAllByPostcode(String postcode);
    void update(Pand pand);
    void aantalPandenMail();
}
