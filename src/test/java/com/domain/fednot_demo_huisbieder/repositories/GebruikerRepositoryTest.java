package com.domain.fednot_demo_huisbieder.repositories;

import com.domain.fednot_demo_huisbieder.entities.Gebruiker;
import com.domain.fednot_demo_huisbieder.entities.Gemeente;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertGemeente.sql")
@Sql("/insertGebruiker.sql")
public class GebruikerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String GEBRUIKERS = "gebruikers";
    @Autowired
    private GebruikerRepository gebruikerRepository;
    @Autowired
    private GemeenteRepository gemeenteRepository;
    private Gebruiker gebruiker;
    private Gemeente gemeente;

    @Before
    public void before() {
        gemeente = new Gemeente("3010", "Kessel-Lo");
        gebruiker = new Gebruiker("test", "test", "testStraat", "1", gemeente, "016121314",
                "test@email.be", "test", "test");
    }

    private long idVanTestGebruiker() {
        return super.jdbcTemplate.queryForObject("select id from gebruikers where gebruikersnaam = 'test'", Long.class);
    }

    private String gebruikersnaamVanTestGebruiker() {
        return super.jdbcTemplate.queryForObject("select gebruikersnaam from gebruikers where gebruikersnaam = 'test'", String.class);
    }

    @Test
    public void create() {
        int aantalGebruikers = super.countRowsInTable(GEBRUIKERS);
        gemeenteRepository.save(gemeente);
        Gebruiker nieuweGebruiker = new Gebruiker("testG", "testG", "testStraat", "1", gemeente, "016121314",
                "test@email.be", "testG", "testG");
        gebruikerRepository.save(nieuweGebruiker);
        assertEquals(aantalGebruikers + 1, super.countRowsInTable(GEBRUIKERS));
    }

    @Test
    public void findById() {
        Optional<Gebruiker> optionalGebruiker = gebruikerRepository.findById(idVanTestGebruiker());
        assertThat(optionalGebruiker.get().getGebruikersnaam()).isEqualTo("test");
    }

    @Test
    public void findByGebruikersnaam() {
        Optional<Gebruiker> optionalGebruiker = gebruikerRepository.findByGebruikersnaam(gebruikersnaamVanTestGebruiker());
        assertThat(optionalGebruiker.get().getGebruikersnaam()).isEqualTo("test");
    }
}
