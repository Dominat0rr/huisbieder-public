package com.domain.fednot_demo_huisbieder.services;

import com.domain.fednot_demo_huisbieder.entities.Gebruiker;

import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface GebruikerService {
    void create(Gebruiker gebruiker);
    Optional<Gebruiker> findById(long id);
    Optional<Gebruiker> findByGebruikersnaam(String gebruikersnaam);
}
