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

public class GemeenteTest {
    private Gemeente gemeente1;
    private Gemeente gemeente1Dubbel;
    private Gemeente gemeente2;
    private Pand pand;

    @Before
    public void before() {
        gemeente1 = new Gemeente("Leuven", "3000");
        gemeente1Dubbel = new Gemeente("Leuven", "3000");
        gemeente2 = new Gemeente("Linden", "3210");
        pand = new Pand(PandType.HUIS, "Huis", "Herenhuis", "test", "1", gemeente1, 1950, 400, null, (byte)2, (byte)3, (byte)1, (byte)2, (byte)3,
                false, false, false, false, false, false, false, false, false, null, false, 500, "Bij de akte", 200_000, LocalDateTime.now(), LocalDateTime.now().plusDays(5),
                "Mooie gerenoveerde woning");
    }

    @Test
    public void eenNullPandToevoegenMislukt() {
        assertThatNullPointerException().isThrownBy(() -> gemeente1.add(null));
    }

    @Test
    public void GemeentenZijnGelijkAlsHunNaamEnPostcodeGelijkZijn() {
        assertThat(gemeente1).isEqualTo(gemeente1Dubbel);
    }

    @Test
    public void GemeentensZijnVerschillendAlsHunNaamEnPostcodeVerschillendZijn() {
        assertThat(gemeente1).isNotEqualTo(gemeente2);
    }

    @Test
    public void eenGemeenteVerschiltVanNull() {
        assertThat(gemeente1).isNotEqualTo(null);
    }

    @Test
    public void eenGemeenteVerschiltVanEnAnderTypeObject() {
        assertThat(gemeente1).isNotEqualTo("");
    }

    @Test
    public void gelijkeGemeentesHebbenDezelfdeHashCode() {
        assertThat(gemeente1).hasSameHashCodeAs(gemeente1Dubbel);
    }

    @Test
    public void gemeente1IsDeGemeenteVanPand1() {
        assertThat(pand.getGemeente()).isEqualTo(gemeente1);
        assertThat(gemeente1.getPanden()).containsOnly(pand);
    }

    @Test
    public void pand1VerhuistNaarGemeente2() {
        assertThat(gemeente2.add(pand)).isTrue();
        assertThat(gemeente2.getPanden()).containsOnly(pand);
        assertThat(pand.getGemeente()).isEqualTo(gemeente2);
    }
}
