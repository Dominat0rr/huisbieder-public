package com.domain.fednot_demo_huisbieder.entities;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class PandTest {
    private Pand pand1;
    private Pand pand1Dubbel;
    private Pand pand2;
    private Gemeente gemeente1;
    private Gemeente gemeente2;
    private Gebruiker gebruiker1;
    private Gebruiker gebruiker2;

    @Before
    public void before() {
        gemeente1 = new Gemeente("Leuven", "3000");
        gemeente2 = new Gemeente("Linden", "3210");
        gebruiker1 = new Gebruiker("test", "test", "testStraat", "12", gemeente1, "016121314", "test@email.be", "test", "testPas");
        gebruiker2 = new Gebruiker("testA", "testA", "testStraatA", "120", gemeente1, "016141312", "testA@email.be", "testA", "testPas");
        pand1 = new Pand(PandType.HUIS, "Huis", "Herenhuis", "test1", "1", gemeente1, 1950, 400, null, (byte)2, (byte)3, (byte)1, (byte)2, (byte)3,
                false, false, null, false, false, null, null, null, null, null, false, 500,
                "Bij de akte", 200_000, LocalDateTime.now(), LocalDateTime.now().plusDays(5), "Mooie recente woning");
        pand1Dubbel = new Pand(PandType.HUIS, "Huis", "Herenhuis", "test1", "1", gemeente1, 1950, 400, null, (byte)2, (byte)3, (byte)1, (byte)2, (byte)3,
                false, false, false, false, false, false, null, null, null, null, false, 500,
                "Bij de akte", 200_000, LocalDateTime.now(), LocalDateTime.now().plusDays(5), "Mooie recente woning");
        pand2 = new Pand(PandType.APPARTEMENT, "Studio", "Studio", "test2", "2", gemeente2, 1980, 550, null, (byte)2, (byte)3, (byte)1, (byte)2, (byte)3,
                false, false, false, null, null, null, false, false, false, null, false, 500,
                "Bij de akte", 300_000, LocalDateTime.now(), LocalDateTime.now().plusDays(7), "Mooie recente woning");
    }

    @Test
    public void eenNullGemeenteInDeConstructorMislukt() {
        assertThatNullPointerException().isThrownBy(()-> new Pand(
                PandType.APPARTEMENT, "Studio", "Studio", "test1", "11", null, 1980, 550, null,
                (byte)2, (byte)3, (byte)1, (byte)2, (byte)3, false, false, null, false, null, false, false, false,
                null, null, false, 500, "Bij de akte", 300_000, LocalDateTime.now(), LocalDateTime.now().plusDays(7),
                "Mooie recente woning"));
    }

    @Test
    public void eenNullGemeenteInSetterMislukt() {
        assertThatNullPointerException().isThrownBy(() -> pand1.setGemeente(null));
    }

    @Test
    public void eenNullGebruikerInSetterMislukt() {
        assertThatNullPointerException().isThrownBy(() -> pand1.setGebruiker(null));
    }

    @Test
    public void pandenZijnGelijkAlsHunStraatnaamHuisnummerEnGemeenteGelijkZijn() {
        assertThat(pand1).isEqualTo(pand1Dubbel);
    }

    @Test
    public void pandenZijnVerschillendAlsHunStraatnaamHuisnummerEnGemeenteVerschillendZijn() {
        assertThat(pand1).isNotEqualTo(pand2);
    }

    @Test
    public void eenPandVerschiltVanNull() {
        assertThat(pand1).isNotEqualTo(null);
    }

    @Test
    public void eePandVerschiltVanEenAnderTypeObject() {
        assertThat(pand1).isNotEqualTo("");
    }

    @Test
    public void gelijkePandenHebbenDezelfdeHashCode() {
        assertThat(pand1).hasSameHashCodeAs(pand1Dubbel);
    }

    @Test
    public void verschillendePandenHebbenVerschillendeHashCode() {
        assertThat(pand1.hashCode()).isNotEqualTo(pand2.hashCode());
    }

    @Test
    public void gebruiker1PlaatsOngeldigBod0() {
        assertThatIllegalArgumentException().isThrownBy(() -> pand1.nieuwBod(gebruiker1, pand1.getHuidigbod() + 0));
    }

    @Test
    public void gebruiker1PlaatsOngeldigBodNegatief() {
        assertThatIllegalArgumentException().isThrownBy(() -> pand1.nieuwBod(gebruiker1, -1));
    }

    @Test
    public void gebruiker1PlaatsOngeldigBod999() {
        assertThatIllegalArgumentException().isThrownBy(() -> pand1.nieuwBod(gebruiker1, pand1.getHuidigbod() + 999));
    }
}
