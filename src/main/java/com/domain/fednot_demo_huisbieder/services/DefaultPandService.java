package com.domain.fednot_demo_huisbieder.services;

import com.domain.fednot_demo_huisbieder.entities.Pand;
import com.domain.fednot_demo_huisbieder.mail.MailSender;
import com.domain.fednot_demo_huisbieder.messaging.PandEnPandURL;
import com.domain.fednot_demo_huisbieder.repositories.PandRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultPandService implements PandService {
    private final PandRepository pandRepository;
    private final EntityManager entityManager;
    private final MailSender mailSender;
    private final JmsTemplate jmsTemplate;
    private final String nieuwPandQueue;

    public DefaultPandService(PandRepository pandRepository, EntityManager entityManager, MailSender mailSender,
                              JmsTemplate jmsTemplate, @Value("${nieuwPandQueue}") String nieuwPandQueue) {
        this.pandRepository = pandRepository;
        this.entityManager = entityManager;
        this.mailSender = mailSender;
        this.jmsTemplate = jmsTemplate;
        this.nieuwPandQueue = nieuwPandQueue;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void create(Pand pand, String pandURL) {
        pandRepository.save(pand);
        //String pandURL = "http://localhost:8080/panden/";
        //mailSender.nieuwPand(pand, pandURL);
        PandEnPandURL pandEnPandURL = new PandEnPandURL(pand, pandURL);
        jmsTemplate.convertAndSend(nieuwPandQueue, pandEnPandURL);
    }

    @Override
    public Optional<Pand> findById(long id) {
        return pandRepository.findById(id);
    }

    @Override
    public List<Pand> findAll() {
        return pandRepository.findAll();
    }

    @Override
    public Page<Pand> findAll(Pageable pageable) {
        return pandRepository.findAll(pageable);
    }

    @Override
    public List<Pand> findBAllByGebruikerId(Long id) {
        return pandRepository.findBAllByGebruikerId(id);
    }

    @Override
    public List<Pand> findAllByGemeenteNaam(String naam) {
        return entityManager.createNamedQuery("Pand.findAllByGemeenteNaam")
                .setParameter("naam", naam)
                .getResultList();
    }

    @Override
    public List<Pand> findAllByPostcode(String postcode) {
        return entityManager.createNamedQuery("Pand.findAllByPostcode")
                .setParameter("postcode", postcode)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void update(Pand pand) {
        pandRepository.save(pand);
    }

    @Override
    //@Scheduled(fixedRate = 120000) // fixedRate = 60000 -> test om de minuut
    @Scheduled(cron = "0 1 1 * * ?")
    public void aantalPandenMail() {
        mailSender.aantalPandenMail(pandRepository.count());
    }
}
