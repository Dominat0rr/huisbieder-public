package com.domain.fednot_demo_huisbieder.forms;

import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class GebruikerRegistratieForm {
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^(\\p{IsAlphabetic}|-|\\s)+", message="Geen geldige voornaam!")
    private final String voornaam;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^(\\p{IsAlphabetic}|-|\\s)+", message="Geen geldige familienaam!")
    private final String familienaam;
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
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message="Geen geldig telefoonnummer!")
    private final String telefoonnummer;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message="Geen geldig email adres!")
    private final String email;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$", message="Geen geldige gebruikersnaam!")
    @Pattern(regexp = "^.{6,}$", message="Gebruikersnaam moet minstens 6 tekens lang zijn!")
    private final String gebruikersnaam;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^.{6,}$", message="Paswoord moet minstens 6 tekens lang zijn!")
    private String paswoord;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^.{6,}$", message="Paswoord moet minstens 6 tekens lang zijn!")
    private String herhaalPaswoord;

    public GebruikerRegistratieForm(String voornaam, String familienaam, String straatnaam, String huisnummer, String postcode, String gemeente,
                                    String telefoonnummer, String email, String gebruikersnaam, String paswoord, String herhaalPaswoord) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.telefoonnummer = telefoonnummer;
        this.email = email;
        this.gebruikersnaam = gebruikersnaam;
        this.paswoord = paswoord;
        this.herhaalPaswoord = herhaalPaswoord;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
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

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public String getEmail() {
        return email;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public String getHerhaalPaswoord() {
        return herhaalPaswoord;
    }

    public boolean isValidPaswoord(String paswoord, String herhaalPaswoord) {
        return paswoord.equals(herhaalPaswoord);
    }
}
