package com.domain.fednot_demo_huisbieder.entities;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class GebruikerTest {
    private Gemeente gemeente1;
    private Gemeente gemeente2;
    private Gebruiker gebruiker1;
    private Gebruiker gebruiker1Dubbel;
    private Gebruiker gebruiker2;
    private Pand pand1;
    private Pand pand2;

    @Before
    public void before() {
        gemeente1 = new Gemeente("Leuven", "3000");
        gemeente2 = new Gemeente("Linden", "3210");
        gebruiker1 = new Gebruiker("test", "test", "testStraat", "12", gemeente1, "016121314", "test@email.be", "test", "testPas");
        gebruiker1Dubbel = new Gebruiker("test", "test", "testStraat", "12", gemeente1, "016121314", "test@email.be", "test", "testPas");
        gebruiker2 = new Gebruiker("testA", "testA", "testStraatA", "120", gemeente1, "016141312", "testA@email.be", "testA", "testPas");
        pand1 = new Pand(PandType.HUIS, "Huis", "Herenhuis", "test1", "1", gemeente1, 1950, 400, null, (byte)2, (byte)3, (byte)1, (byte)2, (byte)3,
                false, false, false, false, false, false, false, false, false, null, false, 500,
                "Bij de akte", 200_000, LocalDateTime.now(), LocalDateTime.now().plusDays(5), "Mooie recente woning");
        pand2 = new Pand(PandType.APPARTEMENT, "Studio", "Studio", "test2", "2", gemeente2, 1980, 550, null, (byte)2, (byte)3, (byte)1, (byte)2, (byte)3,
                false, false, false, false, false, false, false, false, false, null, false, 500,
                "Bij de akte", 300_000, LocalDateTime.now(), LocalDateTime.now().plusDays(7), "Mooie recente woning");
    }

    @Test
    public void eenNullGemeenteInDeConstructorMislukt() {
        assertThatNullPointerException().isThrownBy(()-> new Gebruiker("test1", "test1", "test1", "1", null, "test", "test@email.be", "test", "test"));
    }

    @Test
    public void gebruikersZijnGelijkAlsHunGebruikersnaamGelijkZijn() {
        assertThat(gebruiker1).isEqualTo(gebruiker1Dubbel);
    }

    @Test
    public void gebruikersZijnVerschillendAlsHunGebruikersnaamVerschillendZijn() {
        assertThat(gebruiker1).isNotEqualTo(gebruiker2);
    }

    @Test
    public void eenGebruikerVerschiltVanNull() {
        assertThat(gebruiker1).isNotEqualTo(null);
    }

    @Test
    public void eenGebruikerVerschiltVanEenAnderTypeObject() {
        assertThat(gebruiker1).isNotEqualTo("");
    }

    @Test
    public void gelijkeGebruikersHebbenDezelfdeHashCode() {
        assertThat(gebruiker1).hasSameHashCodeAs(gebruiker1Dubbel);
    }

    @Test
    public void verschillendeGebruikersHebbenVerschillendeHashCode() {
        assertThat(gebruiker1.hashCode()).isNotEqualTo(gebruiker2.hashCode());
    }
}
