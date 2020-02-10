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
class NieuwPandListener {
    private final MailSender mailSender;

    NieuwPandListener(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @JmsListener(destination = "${nieuwPandQueue}")
    void ontvangboodschap(PandEnPandURL pandEnPandURL) {
        mailSender.nieuwPand(pandEnPandURL.getPand(), pandEnPandURL.getPandURL());
    }
}
