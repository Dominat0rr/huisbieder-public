package com.domain.fednot_demo_huisbieder.messaging;

import com.domain.fednot_demo_huisbieder.mail.MailSender;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Component
class NieuweGebruikerListener {
    private final MailSender mailSender;

    NieuweGebruikerListener(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @JmsListener(destination = "${nieuweGebruikerQueue}")
    void ontvangBoodschap(GebruikerMsg gebruikerMsg) {
        mailSender.nieuweGebruiker(gebruikerMsg.getGebruiker());
    }
}
