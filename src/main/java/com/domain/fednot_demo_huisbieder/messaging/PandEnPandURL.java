package com.domain.fednot_demo_huisbieder.messaging;

import com.domain.fednot_demo_huisbieder.entities.Pand;

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
public class PandEnPandURL implements Serializable {
    private static final long serialVersionUID = 1L;
    private Pand pand;
    private String pandURL;

    protected PandEnPandURL() { }

    public PandEnPandURL(Pand pand, String pandURL) {
        this.pand = pand;
        this.pandURL = pandURL;
    }

    public Pand getPand() {
        return pand;
    }

    public String getPandURL() {
        return pandURL;
    }
}
