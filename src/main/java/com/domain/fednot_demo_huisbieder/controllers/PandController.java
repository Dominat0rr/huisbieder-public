package com.domain.fednot_demo_huisbieder.controllers;

import com.domain.fednot_demo_huisbieder.entities.Gebruiker;
import com.domain.fednot_demo_huisbieder.entities.Pand;
import com.domain.fednot_demo_huisbieder.forms.BodForm;
import com.domain.fednot_demo_huisbieder.services.GebruikerService;
import com.domain.fednot_demo_huisbieder.services.PandService;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping(path = "panden", produces = MediaType.TEXT_HTML_VALUE)
public class PandController {
    private final PandService pandService;
    private final GebruikerService gebruikerService;

    public PandController(PandService pandService, GebruikerService gebruikerService) {
        this.pandService = pandService;
        this.gebruikerService = gebruikerService;
    }

    @GetMapping("{id}")
    public ModelAndView pand(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("pand");
        pandService.findById(id).ifPresent(pand -> {
            modelAndView.addObject(pand);
            pand.checkDatumVsNu();
            if (!pand.getDatumIsVoorbij()) {
                BodForm form = new BodForm(null);
                modelAndView.addObject(form);
            }
        });
        return modelAndView;
    }

    @PostMapping("{id}/bieden")
    public ModelAndView bieden(@PathVariable long id, @Valid BodForm bodForm, Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("redirect:/panden/{id}");
        }
        else {
            ModelAndView modelAndViewError = new ModelAndView("pand");
            Optional<Pand> pand = pandService.findById(id);
            if (pand.isPresent()) {
                modelAndViewError.addObject(pand.get());
            }
            pand.get().checkDatumVsNu();
            if (!pand.get().getDatumIsVoorbij()) {
                BodForm form = new BodForm(null);
                modelAndViewError.addObject(form);
            }

            if (pand.get().getEinddatum().isBefore(LocalDateTime.now())) {
                return modelAndViewError.addObject("foutboodschap", "De einddatum van dit pand is al voorbij");
            }
            else if (bodForm.getBod() < pand.get().getHuidigbod() + 1000) {
                return modelAndViewError.addObject("foutboodschap", "Je bod moet minstens €1000 hoger zijn dan het huidige bod");
            }
            else {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                Optional<Gebruiker> gebruiker = gebruikerService.findByGebruikersnaam(username);
                pand.get().nieuwBod(gebruiker.get(), bodForm.getBod());
                pandService.update(pand.get());
            }

            return new ModelAndView("redirect:/panden/{id}");
        }
    }
}
