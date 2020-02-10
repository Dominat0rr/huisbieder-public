package com.domain.fednot_demo_huisbieder.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Entity
@Table(name = "gemeenten")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Gemeente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Column(name = "naam")
    @JsonIgnore
    private String naam;
    @NotBlank
    @JsonIgnore
    private String postcode;
    @OneToMany(mappedBy = "gemeente")
    @OrderBy("naam")
    private Set<Pand> panden;

    /**
     * Default constructor for JPA
     */
    protected Gemeente() { }

    /**
     * Constructor for Gemeente object
     * @param postcode
     * @param naam
     */
    public Gemeente(String postcode, String naam) {
        this.postcode = postcode;
        this.naam = naam;
        this.panden = new LinkedHashSet<>();
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getPostcode() {
        return postcode;
    }

    @JsonIgnore
    public String getUppercaseNaam() {
        return naam.toUpperCase();
    }

    @JsonIgnore
    public String getStructure() {
        return postcode + " " + naam;
    }

    public Set<Pand> getPanden() {
        return Collections.unmodifiableSet(panden);
    }

    public boolean add(Pand pand) {
        boolean toegevoegd = panden.add(pand);
        Gemeente oudeGemeente = pand.getGemeente();

        if (oudeGemeente != null && oudeGemeente != this) oudeGemeente.panden.remove(pand);
        if (this != oudeGemeente) pand.setGemeente(this);

        return toegevoegd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gemeente)) return false;
        Gemeente gemeente = (Gemeente) o;
        return Objects.equals(naam, gemeente.naam) &&
                Objects.equals(postcode, gemeente.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam, postcode);
    }

    @Override
    public String toString() {
        return "Gemeente{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", postcode='" + postcode + '\'' +
                ", panden=" + panden +
                '}';
    }
}