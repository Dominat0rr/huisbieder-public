package com.domain.fednot_demo_huisbieder.controllers;

import com.domain.fednot_demo_huisbieder.entities.Gemeente;
import com.domain.fednot_demo_huisbieder.entities.Pand;
import com.domain.fednot_demo_huisbieder.forms.GemeenteForm;
import com.domain.fednot_demo_huisbieder.services.GemeenteService;
import com.domain.fednot_demo_huisbieder.services.PandService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping(path = "gemeenten", produces = MediaType.TEXT_HTML_VALUE)
public class GemeenteController {
    private final GemeenteService gemeenteService;
    private final PandService pandService;
    private String zoekterm;

    public GemeenteController(GemeenteService gemeenteService, PandService pandService) {
        this.gemeenteService = gemeenteService;
        this.pandService = pandService;
    }

    @PostMapping
    ModelAndView gemeenteZoekTerm(@Valid GemeenteForm gemeenteForm, Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("/");
        }
        zoekterm = gemeenteForm.getZoekTerm();
        return new ModelAndView("redirect:/gemeenten/panden");
    }

    @GetMapping("/panden")
    ModelAndView gevondenPanden () {
        List<Pand> panden = pandService.findAllByGemeenteNaam(zoekterm);
        if (panden.isEmpty()) {
            panden = pandService.findAllByPostcode(zoekterm);
        }
        panden.forEach(pand -> pand.checkDatumVsNu());

        List<Gemeente> gemeenten = gemeenteService.findAllByNaam(zoekterm);
        Optional<Gemeente> optionalGemeente = null;
        if (gemeenten.isEmpty()) {
            optionalGemeente = gemeenteService.findByPostcode(zoekterm);
            if (gemeenten.isEmpty() && !optionalGemeente.isPresent()) {
                return new ModelAndView("redirect:/");
            }
        }

        ModelAndView modelAndView = new ModelAndView("panden");
        if (!gemeenten.isEmpty()) modelAndView.addObject("gemeente", gemeenten.get(0));
        else if (optionalGemeente.isPresent()) modelAndView.addObject("gemeente", optionalGemeente.get());
        modelAndView.addObject("panden", panden);
        return modelAndView;
    }
}
