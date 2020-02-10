package com.domain.fednot_demo_huisbieder.repositories;

import com.domain.fednot_demo_huisbieder.entities.Pand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface PandRepository extends JpaRepository<Pand, Long> {
    @Override
    Page<Pand> findAll(Pageable pageable);
    List<Pand> findBAllByGebruikerId(Long id);
    List<Pand> findAllByGemeenteNaam(String naam);
    List<Pand> findAllByPostcode(String postcode);
}
