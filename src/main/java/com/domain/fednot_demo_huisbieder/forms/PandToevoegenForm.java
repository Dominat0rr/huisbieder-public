package com.domain.fednot_demo_huisbieder.forms;

import com.domain.fednot_demo_huisbieder.entities.PandType;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class PandToevoegenForm {
    @NotNull
    private PandType typeWoning;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message="Geen geldige naam!")
    private final String naam;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message="Geen geldige titel!")
    private final String titel;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^(\\p{IsAlphabetic}|-|\\s)+", message="Geen geldige straatnaam!")
    private final String straatnaam;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^\\d{1,4}(?:[a-zA-z]{1,2}\\d{0,3})?$", message="Geen geldig huisnummer!")
    private final String huisnummer;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^(?:(?:[1-9])(?:\\d{3}))$", message="Geen geldige postcode!")
    private final String postcode;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^(\\p{IsAlphabetic}|-|\\s)+", message="Geen geldige gemeente!")
    private final String gemeente;
    @Positive
    @Range(min = 1, max = 9_999)
    private final Integer bouwjaar;
    @Positive
    @Range(min = 1, max = 99_999)
    private final Integer oppervlakte;
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    //@Pattern(regexp = "^\\p{L}+(?:\\s\\p{L}+)*$", message="Geen geldige staat!")
    private final String staatgebouw;
    @Positive
    @Range(min = 1, max = 9)
    private final Byte aantalgevels;
    @Positive
    @Range(min = 1, max = 9)
    private final Byte aantalslaapkamers;
    @Positive
    @Range(min = 1, max = 9)
    private final Byte aantalbadkamers;
    @Positive
    @Range(min = 1, max = 9)
    private final Byte aantaltoiletten;
    @Positive
    @Range(min = 1, max = 9)
    private final Byte aantalverdiepingen;
    private final Boolean living;
    private final Boolean eetkamer;
    private final Boolean bureau;
    private final Boolean kelder;
    private final Boolean zolder;
    private final Boolean garage;
    private final Boolean tuin;
    private final Boolean terras;
    private final Boolean veranda;
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private final String code_epc;
    private final Boolean dubbelebegelazing;
    @Positive
    @Range(min = 1, max = 9_999)
    private final Integer kadastraalinkomen;
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    //@Pattern(regexp = "^[a-zA-Z\\s]*$}+")
    private final String vrijVanaf;
    @Positive
    private final Integer startbod;
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime startdatum;
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime einddatum;
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    //@Pattern(regexp = "^(\\p{IsAlphabetic}|-)+")
    private final String omschrijving;

    public PandToevoegenForm(Integer typeWoning, String naam, String titel, String straatnaam, String huisnummer, String postcode, String gemeente,
                             Integer bouwjaar, Integer oppervlakte, String staatgebouw, Byte aantalgevels, Byte aantalslaapkamers, Byte aantalbadkamers,
                             Byte aantaltoiletten, Byte aantalverdiepingen, Boolean living, Boolean eetkamer, Boolean bureau, Boolean kelder,
                             Boolean zolder, Boolean garage, Boolean tuin, Boolean terras, Boolean veranda, String code_epc, Boolean dubbelebegelazing,
                             Integer kadastraalinkomen, String vrijVanaf, Integer startbod, String startdatum, String einddatum, String omschrijving) {
        if (typeWoning != null) {
            switch (typeWoning) {
                case 0: this.typeWoning = PandType.APPARTEMENT; break;
                case 1: this.typeWoning = PandType.COMMERCIEEL; break;
                case 2: this.typeWoning = PandType.GARAGE; break;
                case 3: this.typeWoning = PandType.HUIS; break;
                case 4: this.typeWoning = PandType.INDUSTRIE; break;
                case 5: this.typeWoning = PandType.KANTOOR; break;
                case 6: this.typeWoning = PandType.GROND; break;
                default: this.typeWoning = PandType.ANDERE; break;
            }
        }
        this.naam = naam;
        this.titel = titel;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.bouwjaar = bouwjaar;
        this.oppervlakte = oppervlakte;
        this.staatgebouw = staatgebouw;
        this.aantalgevels = aantalgevels;
        this.aantalslaapkamers = aantalslaapkamers;
        this.aantalbadkamers = aantalbadkamers;
        this.aantaltoiletten = aantaltoiletten;
        this.aantalverdiepingen = aantalverdiepingen;
        this.living = living;
        this.eetkamer = eetkamer;
        this.bureau = bureau;
        this.kelder = kelder;
        this.zolder = zolder;
        this.garage = garage;
        this.tuin = tuin;
        this.terras = terras;
        this.veranda = veranda;
        this.code_epc = code_epc;
        this.dubbelebegelazing = dubbelebegelazing;
        this.kadastraalinkomen = kadastraalinkomen;
        this.vrijVanaf = vrijVanaf;
        this.startbod = startbod;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        if (startdatum != null) this.startdatum = LocalDateTime.parse(startdatum, formatter);
        else this.startdatum = null;
        if (einddatum != null) this.einddatum = LocalDateTime.parse(einddatum, formatter);
        else this.einddatum = null;
        this.omschrijving = omschrijving;
    }

    public PandType getTypeWoning() {
        return typeWoning;
    }

    public String getNaam() {
        return naam;
    }

    public String getTitel() {
        return titel;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public Integer getBouwjaar() {
        return bouwjaar;
    }

    public Integer getOppervlakte() {
        return oppervlakte;
    }

    public String getStaatgebouw() {
        return staatgebouw;
    }

    public Byte getAantalgevels() {
        return aantalgevels;
    }

    public Byte getAantalslaapkamers() {
        return aantalslaapkamers;
    }

    public Byte getAantalbadkamers() {
        return aantalbadkamers;
    }

    public Byte getAantaltoiletten() {
        return aantaltoiletten;
    }

    public Byte getAantalverdiepingen() {
        return aantalverdiepingen;
    }

    public Boolean getLiving() {
        return living;
    }

    public Boolean getEetkamer() {
        return eetkamer;
    }

    public Boolean getBureau() {
        return bureau;
    }

    public Boolean getKelder() {
        return kelder;
    }

    public Boolean getZolder() {
        return zolder;
    }

    public Boolean getGarage() {
        return garage;
    }

    public Boolean getTuin() {
        return tuin;
    }

    public Boolean getTerras() {
        return terras;
    }

    public Boolean getVeranda() {
        return veranda;
    }

    public String getCode_epc() {
        return code_epc;
    }

    public Boolean getDubbelebegelazing() {
        return dubbelebegelazing;
    }

    public Integer getKadastraalinkomen() {
        return kadastraalinkomen;
    }

    public String getVrijVanaf() {
        return vrijVanaf;
    }

    public Integer getStartbod() {
        return startbod;
    }

    public LocalDateTime getStartdatum() {
        return startdatum;
    }

    public LocalDateTime getEinddatum() {
        return einddatum;
    }

    public String getOmschrijving() {
        return omschrijving;
    }
}
