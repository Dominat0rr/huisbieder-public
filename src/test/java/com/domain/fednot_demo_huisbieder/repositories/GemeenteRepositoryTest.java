package com.domain.fednot_demo_huisbieder.repositories;

import com.domain.fednot_demo_huisbieder.entities.Gemeente;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertGemeente.sql")
public class GemeenteRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String GEMEENTEN = "gemeenten";
    @Autowired
    private GemeenteRepository gemeenteRepository;
    private Gemeente gemeente1;
    private Gemeente gemeente2;

    @Before
    public void before() {
        gemeente1 = new Gemeente("3010", "Kessel-Lo");
        gemeente2 = new Gemeente("3210", "Linden");
    }

    private long idVanTestGemeente1() {
        return super.jdbcTemplate.queryForObject("select id from gemeenten where naam like 'Kessel-Lo'", Long.class);
    }

    private long idVanTestGemeente2() {
        return super.jdbcTemplate.queryForObject("select id from gemeenten where naam like 'Linden'", Long.class);
    }

    @Test
    public void create() {
        int aantalGemeenten = super.countRowsInTable(GEMEENTEN);
        Gemeente nieuweGemeente = new Gemeente("8000", "Brugge");
        gemeenteRepository.save(nieuweGemeente);
        assertEquals(aantalGemeenten + 1, super.countRowsInTable(GEMEENTEN));
    }

    @Test
    public void findAll() {
        List<Gemeente> gemeenten = gemeenteRepository.findAll();
        assertThat(gemeenten.size()).isEqualTo(2);
    }

    @Test
    public void findById() {
        Optional<Gemeente> gemeente = gemeenteRepository.findById(idVanTestGemeente1());
        assertThat(gemeente.get().getNaam()).isEqualTo("Kessel-Lo");
    }

    @Test
    public void findByPostcode() {
        Optional<Gemeente> optionalGemeente = gemeenteRepository.findByPostcode("3010");
        assertThat(optionalGemeente.get().getPostcode()).isEqualTo("3010");
    }

    @Test
    public void findByNaam() {
        Optional<Gemeente> optionalGemeente = gemeenteRepository.findByNaam("Linden");
        assertThat(optionalGemeente.get().getNaam()).isEqualTo("Linden");
    }

    @Test
    public void findAllByNaam() {
        List<Gemeente> gemeenten = gemeenteRepository.findAllByNaam("Linden");
        assertThat(gemeenten.size()).isEqualTo(1);
    }

    @Test
    public void findByNaamContainingIgnoreCase() {
        List<Gemeente> gemeenten = gemeenteRepository.findAllByNaamContainingIgnoreCase("Kessel");
        assertThat(gemeenten.get(0).getNaam()).contains("Kessel");
        assertThat(gemeenten.size()).isEqualTo(1);
    }
}
