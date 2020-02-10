package com.domain.fednot_demo_huisbieder.entities;

import com.domain.fednot_demo_huisbieder.adapters.LocalDateTimeAdapter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Entity
@Table(name = "panden")
@NamedQuery(name = "Pand.findAllByGemeenteNaam", query = "select p from Pand p inner join Gemeente g on p.gemeente = g.id where g.naam = :naam")
@NamedQuery(name = "Pand.findAllByPostcode", query = "select p from Pand p inner join Gemeente g on p.gemeente = g.id where g.postcode = :postcode")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Pand implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @DateTimeFormat(style = "SS")
    @XmlTransient
    @JsonIgnore
    private LocalDateTime datumToegevoegd;
    @NotNull
    private PandType typeWoning;
    @NotNull
    @Size(min = 2, message = "Naam moet minstens 2 karakters lang zijn")
    private String naam;
    @NotBlank
    private String titel;
    @NotBlank
    private String straatnaam;
    @NotBlank
    private String huisnummer;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gemeenteId")
    @XmlTransient
    @JsonIgnore
    private Gemeente gemeente;
    @Transient
    private String gemeenteNaam;
    @Transient
    private String postcode;
    @PositiveOrZero
    private Integer bouwjaar;
    @Positive
    private Integer oppervlakte;
    private String staatGebouw;
    @Positive
    private Byte aantalgevels;
    @Positive
    private Byte aantalslaapkamers;
    @Positive
    private Byte aantalbadkamers;
    @Positive
    private Byte aantaltoiletten;
    @Positive
    private Byte aantalverdiepingen;
    private Boolean living;
    private Boolean eetkamer;
    private Boolean bureau;
    private Boolean kelder;
    private Boolean zolder;
    private Boolean garage;
    private Boolean tuin;
    private Boolean terras;
    private Boolean veranda;
    private String code_epc;
    private Boolean dubbelebegelazing;
    private Integer kadastraalinkomen;
    private String vrijVanaf;
    @NotNull
    @Positive
    private int startbod;
    private Integer huidigbod;
    private Long gebruikerId;
    @NotNull
    @DateTimeFormat(style = "SS")
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    private LocalDateTime startdatum;
    @NotNull
    @DateTimeFormat(style = "SS")
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    private LocalDateTime einddatum;
    @Transient
    @JsonIgnore
    private boolean datumIsVoorbij = false;
    @Transient
    @JsonIgnore
    private boolean datumMoetNogKomen = false;
    private String omschrijving;
    @Transient
    @JsonIgnore
    private String omschrijvingPreview;

    /**
     * Default constructor for JPA
     */
    protected Pand() { }

    /**
     * Constructor for Pand object
     * @param typeWoning
     * @param naam
     * @param titel
     * @param straatnaam
     * @param huisnummer
     * @param gemeente
     * @param bouwjaar
     * @param oppervlakte
     * @param staatGebouw
     * @param aantalgevels
     * @param aantalslaapkamers
     * @param aantalbadkamers
     * @param aantaltoiletten
     * @param aantalverdiepingen
     * @param living
     * @param eetkamer
     * @param bureau
     * @param kelder
     * @param zolder
     * @param garage
     * @param tuin
     * @param terras
     * @param veranda
     * @param code_epc
     * @param dubbelebegelazing
     * @param kadastraalinkomen
     * @param vrijVanaf
     * @param startbod
     * @param startdatum
     * @param einddatum
     * @param omschrijving
     */
    public Pand(PandType typeWoning, String naam, String titel, String straatnaam, String huisnummer, Gemeente gemeente, Integer bouwjaar,
                Integer oppervlakte, String staatGebouw, Byte aantalgevels, Byte aantalslaapkamers, Byte aantalbadkamers, Byte aantaltoiletten,
                Byte aantalverdiepingen, Boolean living, Boolean eetkamer, Boolean bureau, Boolean kelder, Boolean zolder, Boolean garage,
                Boolean tuin, Boolean terras, Boolean veranda, String code_epc, Boolean dubbelebegelazing, Integer kadastraalinkomen,
                String vrijVanaf, int startbod, LocalDateTime startdatum, LocalDateTime einddatum, String omschrijving) throws NullPointerException {
        this.datumToegevoegd = LocalDateTime.now();
        this.typeWoning = typeWoning;
        this.naam = naam;
        this.titel = titel;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        setGemeente(gemeente);
        this.bouwjaar = bouwjaar;
        this.oppervlakte = oppervlakte;
        this.staatGebouw = staatGebouw;
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
        this.huidigbod = startbod;
        this.gebruikerId = null;
        this.startdatum = startdatum;
        this.einddatum = einddatum;
        this.omschrijving = omschrijving;
    }

    // Getters
    public long getId() {
        return id;
    }

    public LocalDateTime getDatumToegevoegd() {
        return datumToegevoegd;
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

    public Gemeente getGemeente() {
        return gemeente;
    }

    public String getGemeenteNaam() {
        return gemeente.getNaam();
    }

    public String getPostcode() {
        return gemeente.getPostcode();
    }

    public Integer getBouwjaar() {
        return bouwjaar;
    }

    public Integer getOppervlakte() {
        return oppervlakte;
    }

    public String getStaatGebouw() {
        return staatGebouw;
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

    public int getStartbod() {
        return startbod;
    }

    public Integer getHuidigbod() {
        return huidigbod;
    }

    public Long getGebruikerId() {
        return gebruikerId;
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

    public String getOmschrijvingPreview() {
        //return omschrijving.substring(0, 150);

        String s = omschrijving;
        int spaceCount = 0;
        int lastIndex = 0;
        String[] stringSplitted = new String[45];
        int stringLength = 0;

        for (int i = 0; i <s.length(); i++) {
            if (s.charAt(i) == ' ') {
                spaceCount++;
            }
            if (spaceCount == 45) {     //after encountering 10 words split the sentence from lastIndex to the 10th word. For the first time lastIndex would be zero that is starting position of the string
                stringSplitted[stringLength++] = s.substring(lastIndex, i);
                lastIndex = i;
                spaceCount = 0;
            }
        }
        stringSplitted[stringLength++] = s.substring(lastIndex,s.length()-1);

        return stringSplitted[0] + "...";
    }

    @JsonIgnore
    public boolean getOmschrijvingPreviewEqualsOmschrijving() {
        return getOmschrijvingPreview().equals(getOmschrijving());
    }

    public boolean getDatumIsVoorbij() {
        return datumIsVoorbij;
    }

    public boolean getDatumMoetNogKomen() {
        return datumMoetNogKomen;
    }

    // Setters
    public void setGemeente(Gemeente gemeente) {
        if (!gemeente.getPanden().contains(this)) gemeente.add(this);
        this.gemeente = gemeente;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruikerId = gebruiker.getId();
    }

    public void setHuidigbod(int bod) {
        if (isValidNieuwBod(bod)) this.huidigbod = bod;
        else throw new IllegalArgumentException("Nieuw bod is te laag");
    }

    public void nieuwBod(Gebruiker gebruiker, int bod) throws NullPointerException, IllegalArgumentException {
        if (gebruiker == null) throw new NullPointerException();
        if (isValidNieuwBod(bod)) {
            this.huidigbod = bod;
            this.gebruikerId = gebruiker.getId();
        }
        else throw new IllegalArgumentException("Nieuw bod is te laag");
    }

    public boolean isValidNieuwBod(int nieuwBod) {
        return huidigbod + 1000 <= nieuwBod;
    }

    public void checkDatumVsNu() {
        if (getEinddatum().isBefore(LocalDateTime.now())) {
            datumIsVoorbij = true;
        }
        if (getStartdatum().isAfter(LocalDateTime.now())) {
            datumMoetNogKomen = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pand)) return false;
        Pand pand = (Pand) o;
        return Objects.equals(straatnaam, pand.straatnaam) &&
                Objects.equals(huisnummer, pand.huisnummer) &&
                Objects.equals(gemeente, pand.gemeente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(straatnaam, huisnummer, gemeente);
    }

    @Override
    public String toString() {
        return "Pand{" +
                "id=" + id +
                ", datumToegevoegd=" + datumToegevoegd +
                ", typeWoning=" + typeWoning +
                ", naam='" + naam + '\'' +
                ", titel='" + titel + '\'' +
                ", straatnaam='" + straatnaam + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", postcode='" + postcode + '\'' +
                ", gemeenteNaam='" + gemeenteNaam + '\'' +
                ", bouwjaar=" + bouwjaar +
                ", oppervlakte=" + oppervlakte +
                ", staatGebouw='" + staatGebouw + '\'' +
                ", aantalgevels=" + aantalgevels +
                ", aantalslaapkamers=" + aantalslaapkamers +
                ", aantalbadkamers=" + aantalbadkamers +
                ", aantaltoiletten=" + aantaltoiletten +
                ", aantalverdiepingen=" + aantalverdiepingen +
                ", living=" + living +
                ", eetkamer=" + eetkamer +
                ", bureau=" + bureau +
                ", kelder=" + kelder +
                ", zolder=" + zolder +
                ", garage=" + garage +
                ", tuin=" + tuin +
                ", terras=" + terras +
                ", veranda=" + veranda +
                ", code_epc='" + code_epc + '\'' +
                ", dubbelebegelazing=" + dubbelebegelazing +
                ", kadastraalinkomen=" + kadastraalinkomen +
                ", vrijVanaf='" + vrijVanaf + '\'' +
                ", startbod=" + startbod +
                ", huidigbod=" + huidigbod +
                ", gebruikerId=" + gebruikerId +
                ", startdatum=" + startdatum +
                ", einddatum=" + einddatum +
                ", omschrijving='" + omschrijving + '\'' +
                '}';
    }
}
