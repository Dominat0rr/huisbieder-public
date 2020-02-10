package com.domain.fednot_demo_huisbieder.mail;

import com.domain.fednot_demo_huisbieder.entities.Gebruiker;
import com.domain.fednot_demo_huisbieder.entities.Pand;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface MailSender {
    void nieuweGebruiker(Gebruiker gebruiker);
    void nieuwPand(Pand pand, String pandURL);
    void aantalPandenMail(long aantal);
}
