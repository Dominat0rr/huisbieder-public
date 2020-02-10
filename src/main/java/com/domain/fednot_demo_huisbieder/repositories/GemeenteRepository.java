package com.domain.fednot_demo_huisbieder.repositories;

import com.domain.fednot_demo_huisbieder.entities.Gemeente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface GemeenteRepository extends JpaRepository<Gemeente, Long> {
    Optional<Gemeente> findByPostcode(String postcode);
    Optional<Gemeente> findByNaam(String naam);
    List<Gemeente> findAllByNaam(String naam);
    List<Gemeente> findAllByNaamContainingIgnoreCase(String str);
}
