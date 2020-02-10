package com.domain.fednot_demo_huisbieder.repositories;

import com.domain.fednot_demo_huisbieder.entities.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface GebruikerRepository extends JpaRepository<Gebruiker, Long> {
    Optional<Gebruiker> findByGebruikersnaam(String gebruikersnaam);
}
