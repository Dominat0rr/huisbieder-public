package com.domain.fednot_demo_huisbieder.repositories;

import com.domain.fednot_demo_huisbieder.entities.Gebruiker;
import com.domain.fednot_demo_huisbieder.entities.Gemeente;
import com.domain.fednot_demo_huisbieder.entities.Pand;
import com.domain.fednot_demo_huisbieder.entities.PandType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
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
@Sql("/insertPand.sql")
public class PandRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String PANDEN = "panden";
    @Autowired
    private PandRepository pandRepository;
    @Autowired
    private GemeenteRepository gemeenteRepository;
    @Autowired
    private GebruikerRepository gebruikerRepository;
    private Gemeente gemeente;
    private Gemeente gemeente2;
    private Gebruiker gebruiker;
    private Pand pand;
    private Pageable pageable = null;

    @Before
    public void before() {
        gemeente = new Gemeente("3010", "Kessel-Lo");
        gemeente2 = new Gemeente("8000", "Brugge");
        gebruiker = new Gebruiker("test", "test", "testStraat", "1", gemeente, "016121314",
                "test@email.be", "test", "test");
        pand = new Pand(PandType.HUIS, "test", "test", "testStraat", "1", gemeente, 1999, 550,
                null, (byte)3, (byte)3, (byte)1, (byte)2, (byte)3, true, true, null,
                null, true, null, null, true, null, null, null, 750,
                "Bij de akte", 250_000, LocalDateTime.now(), LocalDateTime.now().plusDays(7), "Mooie woning");
    }

    private long idVanTestGebruiker() {
        return super.jdbcTemplate.queryForObject("select id from gebruikers where gebruikersnaam = 'test'", Long.class);
    }

    private long idVanTestPand() {
        return super.jdbcTemplate.queryForObject("select id from panden where naam = 'Herenhuis'", Long.class);
    }

    @Test
    public void create() {
        int aantalPanden = super.countRowsInTable(PANDEN);
        gemeenteRepository.save(gemeente2);
        Pand nieuwPand = new Pand(PandType.HUIS, "test1", "test1", "testStraat1", "11", gemeente2, 1999, 550,
                null, (byte)3, (byte)3, (byte)1, (byte)2, (byte)3, true, true, null,
                true, true, null, true, true, null, null, null, 750,
                "Bij de akte", 250_000, LocalDateTime.now(), LocalDateTime.now().plusDays(7), "Mooie woning");
        pandRepository.save(nieuwPand);
        assertEquals(aantalPanden + 1, super.countRowsInTable(PANDEN));
    }

    @Test
    public void findById() {
        Optional<Pand> optinalPand = pandRepository.findById(idVanTestPand());
        assertThat(optinalPand.get().getNaam()).isEqualTo("Herenhuis");
    }

    @Test
    public void findAll() {
        List<Pand> panden = pandRepository.findAll();
        assertThat(panden.size()).isEqualTo(2);
    }

    @Test
    public void findAllByGebruikerId() {
        Optional<Gebruiker> optionalGebruiker = gebruikerRepository.findById(idVanTestGebruiker());
        pand.nieuwBod(optionalGebruiker.get(), pand.getHuidigbod() + 1500);
        assertThat(pand.getGebruikerId()).isEqualTo(idVanTestGebruiker());
    }

    @Test
    public void findAllByGemeenteNaam() {
        List<Pand> panden = pandRepository.findAllByGemeenteNaam("Kessel-Lo");
        assertThat(panden.size()).isEqualTo(1);
    }

    @Test
    public void findAllByPostcode() {
        List<Pand> panden = pandRepository.findAllByPostcode("3010");
        assertThat(panden.size()).isEqualTo(1);
    }

    @Test
    public void update() {
        Optional<Pand> optionalPand = pandRepository.findById(idVanTestPand());
        int huidigbod = optionalPand.get().getHuidigbod();
        optionalPand.get().nieuwBod(gebruiker, huidigbod + 2500);
        pandRepository.save(optionalPand.get());

        Optional<Pand> optionalPand1 = pandRepository.findById(idVanTestPand());
        assertThat(optionalPand1.get().getHuidigbod()).isEqualTo(huidigbod + 2500);
        assertThat(optionalPand1.get().getGebruikerId()).isEqualTo(gebruiker.getId());
    }
}
