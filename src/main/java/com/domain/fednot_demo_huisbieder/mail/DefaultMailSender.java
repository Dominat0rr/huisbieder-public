package com.domain.fednot_demo_huisbieder.mail;

import com.domain.fednot_demo_huisbieder.entities.Gebruiker;
import com.domain.fednot_demo_huisbieder.entities.Pand;
import com.domain.fednot_demo_huisbieder.exceptions.KanEmailNietVerzendenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Component
public class DefaultMailSender implements MailSender {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JavaMailSender mailSender;
    private final String emailAdresWebMaster;
    private static final String websiteURL = "localhost:8080";

    public DefaultMailSender(JavaMailSender mailSender, @Value("${emailAdresWebMaster}") String emailAdresWebMaster) {
        this.mailSender = mailSender;
        this.emailAdresWebMaster = emailAdresWebMaster;
    }

    @Override
    @Async
    public void nieuweGebruiker(Gebruiker gebruiker) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(gebruiker.getEmail());
            helper.setSubject("Nieuwe gebruiker");
            helper.setText("Uw account is succesvol aangemaakt bij <a href='" + websiteURL + "'>Huisbieder</a>.\n Uw gebruikersnaam is: <strong>" + gebruiker.getGebruikersnaam() + "</strong>.",true);
            mailSender.send(message);
        } catch (MessagingException | MailException ex) {
            logger.error("Kan mail nieuwe gebruiker niet versturen", ex);
            throw new KanEmailNietVerzendenException();
        }
    }

    @Override
    @Async
    public void nieuwPand(Pand pand, String pandURL) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(emailAdresWebMaster);
            helper.setSubject("Nieuw pand toegevoegt");
            String url = pandURL + pand.getId();
            helper.setText("Er is een nieuw pand toegevoegd en heeft het nummer <strong>" + pand.getId() + "</strong>. Je vindt het pand <a href='" + url + "'>hier</a>.",true);
            mailSender.send(message);
        } catch (MessagingException | MailException ex) {
            logger.error("Kan mail nieuw pand niet versturen", ex);
            throw new KanEmailNietVerzendenException();
        }
    }

    @Override
    //@Async
    public void aantalPandenMail(long aantal) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(emailAdresWebMaster);
            helper.setSubject("Aantal panden");
            helper.setText("Aantal panden:<strong>" + aantal + "</strong>", true);
            mailSender.send(message);
        } catch (MessagingException | MailException ex) {
            logger.error("Kan mail aantal panden niet versturen", ex);
            throw new KanEmailNietVerzendenException();
        }
    }
}
