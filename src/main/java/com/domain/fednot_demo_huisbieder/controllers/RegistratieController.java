package com.domain.fednot_demo_huisbieder.controllers;

import com.domain.fednot_demo_huisbieder.entities.Gebruiker;
import com.domain.fednot_demo_huisbieder.entities.Gemeente;
import com.domain.fednot_demo_huisbieder.forms.GebruikerRegistratieForm;
import com.domain.fednot_demo_huisbieder.services.GebruikerService;
import com.domain.fednot_demo_huisbieder.services.GemeenteService;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping(path = "nieuwegebruiker", produces = MediaType.TEXT_HTML_VALUE)
public class RegistratieController {
    private final GebruikerService gebruikerService;
    private final GemeenteService gemeenteService;

    public RegistratieController(GebruikerService gebruikerService, GemeenteService gemeenteService) {
        this.gebruikerService = gebruikerService;
        this.gemeenteService = gemeenteService;
    }

    @GetMapping()
    public ModelAndView nieuwegebruiker() {
        ModelAndView modelAndView = new ModelAndView("nieuwegebruiker")
                .addObject(new GebruikerRegistratieForm(null, null, null, null,
                        null, null, null, null, null, null, null));
        return modelAndView;
    }

    @PostMapping("/registratie")
    public ModelAndView registratie(@Valid @Validated GebruikerRegistratieForm form, Errors errors, HttpServletRequest request, RedirectAttributes redirect) {
        ModelAndView modelAndView = new ModelAndView("nieuwegebruiker");
        if (errors.hasErrors()) return modelAndView;
        if (gebruikerService.findByGebruikersnaam(form.getGebruikersnaam()).isPresent()) {
            ModelAndView modelAndViewError = new ModelAndView("nieuwegebruiker");
            return modelAndViewError.addObject("foutboodschap", "Gebruikersnaam moet uniek zijn");
        }

        Optional<Gemeente> gemeente = gemeenteService.findByPostcode(form.getPostcode());
        Gebruiker gebruiker;
        if (!gemeente.isPresent()) {
            Gemeente nieuweGemeente = new Gemeente(form.getPostcode(), form.getGemeente());
            gemeenteService.create(nieuweGemeente);
            gebruiker = new Gebruiker(form.getVoornaam(), form.getFamilienaam(), form.getStraatnaam(), form.getHuisnummer(), nieuweGemeente,
                    form.getTelefoonnummer(), form.getEmail(), form.getGebruikersnaam(), new BCryptPasswordEncoder().encode(form.getPaswoord()));
        }
        else {
            gebruiker = new Gebruiker(form.getVoornaam(), form.getFamilienaam(), form.getStraatnaam(), form.getHuisnummer(), gemeente.get(),
                    form.getTelefoonnummer(), form.getEmail(), form.getGebruikersnaam(), new BCryptPasswordEncoder().encode(form.getPaswoord()));
        }

        if (form.isValidPaswoord(form.getPaswoord(), form.getHerhaalPaswoord())) {
            gebruikerService.create(gebruiker);
            try {
                request.login(form.getGebruikersnaam(), form.getPaswoord());
            } catch (ServletException e) {
                e.printStackTrace();
            }
            return new ModelAndView("redirect:/");
        }
        else {
            ModelAndView modelAndViewError = new ModelAndView("nieuwegebruiker");
            return modelAndViewError.addObject("foutboodschap", "Paswoord en herhaal paswoord moet gelijk zijn");
        }
    }
}
