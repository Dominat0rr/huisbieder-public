package com.domain.fednot_demo_huisbieder.services;

import com.domain.fednot_demo_huisbieder.entities.Gebruiker;
import com.domain.fednot_demo_huisbieder.mail.MailSender;
import com.domain.fednot_demo_huisbieder.messaging.GebruikerMsg;
import com.domain.fednot_demo_huisbieder.repositories.GebruikerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultGebruikerService implements GebruikerService {
    private final GebruikerRepository gebruikerRepository;
    private final MailSender mailSender;
    private final JmsTemplate jmsTemplate;
    private final String nieuweGebruikerQueue;

    public DefaultGebruikerService(GebruikerRepository gebruikerRepository, MailSender mailSender,
                                   JmsTemplate jmsTemplate, @Value("${nieuweGebruikerQueue}") String nieuweGebruikerQueue) {
        this.gebruikerRepository = gebruikerRepository;
        this.mailSender = mailSender;
        this.jmsTemplate = jmsTemplate;
        this.nieuweGebruikerQueue = nieuweGebruikerQueue;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void create(Gebruiker gebruiker) {
        gebruikerRepository.save(gebruiker);
        mailSender.nieuweGebruiker(gebruiker);
        //GebruikerMsg gebruikerMsg = new GebruikerMsg(gebruiker);
        //jmsTemplate.convertAndSend(nieuweGebruikerQueue, gebruikerMsg);
    }

    @Override
    public Optional<Gebruiker> findById(long id) {
        return gebruikerRepository.findById(id);
    }

    @Override
    public Optional<Gebruiker> findByGebruikersnaam(String gebruikersnaam) {
        return gebruikerRepository.findByGebruikersnaam(gebruikersnaam);
    }
}
