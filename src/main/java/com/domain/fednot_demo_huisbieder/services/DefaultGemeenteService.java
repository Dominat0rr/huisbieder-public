package com.domain.fednot_demo_huisbieder.services;

import com.domain.fednot_demo_huisbieder.entities.Gemeente;
import com.domain.fednot_demo_huisbieder.repositories.GemeenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultGemeenteService implements GemeenteService {
    private final GemeenteRepository gemeenteRepository;

    public DefaultGemeenteService(GemeenteRepository gemeenteRepository) {
        this.gemeenteRepository = gemeenteRepository;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void create(Gemeente gemeente) {
        gemeenteRepository.save(gemeente);
    }

    @Override
    public List<Gemeente> findAll() {
        return gemeenteRepository.findAll();
    }

    @Override
    public Optional<Gemeente> findById(long id) {
        return gemeenteRepository.findById(id);
    }

    @Override
    public Optional<Gemeente> findByPostcode(String postcode) {
        return gemeenteRepository.findByPostcode(postcode);
    }

    @Override
    public Optional<Gemeente> findByNaam(String naam) {
        return gemeenteRepository.findByNaam(naam);
    }

    @Override
    public List<Gemeente> findAllByNaam(String naam) {
        return gemeenteRepository.findAllByNaam(naam);
    }

    @Override
    public List<Gemeente> findAllByNaamContaining(String str) {
        return gemeenteRepository.findAllByNaamContainingIgnoreCase(str);
    }
}
