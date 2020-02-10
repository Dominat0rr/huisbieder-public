package com.domain.fednot_demo_huisbieder.messaging;

import com.domain.fednot_demo_huisbieder.entities.Gebruiker;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GebruikerMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    private Gebruiker gebruiker;

    protected GebruikerMsg() { }

    public GebruikerMsg(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }
}
