package com.domain.fednot_demo_huisbieder.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Entity
@Table(name = "gebruikers")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Gebruiker implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String voornaam;
    @NotBlank
    private String familienaam;
    @NotBlank
    private String straatnaam;
    @NotBlank
    private String huisnummer;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gemeenteId")
    private Gemeente gemeente;
    private String telefoonnummer;
    @NotBlank
    private String email;
    @NotBlank
    private String gebruikersnaam;
    @NotBlank
    private String paswoord;

    @ManyToMany
    @JoinTable(name = "gebruikersrollen",
            joinColumns = @JoinColumn(name = "gebruikerId"),
            inverseJoinColumns = @JoinColumn(name = "rolId"))
    private Set<Rol> rollen = new HashSet<>();

    /**
     * Default constructor for JPA
     */
    protected Gebruiker() { }

    /**
     * Constructor for Gebruiker object
     * @param voornaam
     * @param familienaam
     * @param straatnaam
     * @param huisnummer
     * @param gemeente
     * @param telefoonnummer
     * @param email
     * @param gebruikersnaam
     * @param paswoord
     * @throws NullPointerException
     */
    public Gebruiker(String voornaam, String familienaam, String straatnaam, String huisnummer, Gemeente gemeente, String telefoonnummer,
                     String email, String gebruikersnaam, String paswoord) throws NullPointerException {
        if (gemeente == null) throw new NullPointerException("Gemeente kan niet null zijn");
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.gemeente = gemeente;
        this.telefoonnummer = telefoonnummer;
        this.email = email;
        this.gebruikersnaam = gebruikersnaam;
        this.paswoord = "{bcrypt}" + paswoord; // "{bcrypt}"
        rollen.add(Rol.KLANT);
    }

    // Getters
    public long getId() {
        return id;
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

    public Gemeente getGemeente() {
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

    public Set<Rol> getRollen() {
        return Collections.unmodifiableSet(rollen);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gebruiker)) return false;
        Gebruiker gebruiker = (Gebruiker) o;
        return gebruikersnaam.equals(gebruiker.gebruikersnaam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gebruikersnaam);
    }

    @Override
    public String toString() {
        return "Gebruiker{" +
                "id=" + id +
                ", voornaam='" + voornaam + '\'' +
                ", familienaam='" + familienaam + '\'' +
                ", straatnaam='" + straatnaam + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", gemeente=" + gemeente +
                ", telefoonnummer='" + telefoonnummer + '\'' +
                ", email='" + email + '\'' +
                ", gebruikersnaam='" + gebruikersnaam + '\'' +
                ", paswoord='" + paswoord + '\'' +
                '}';
    }
}
