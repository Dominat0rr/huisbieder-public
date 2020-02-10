package com.domain.fednot_demo_huisbieder.controllers;

import com.domain.fednot_demo_huisbieder.entities.Pand;
import com.domain.fednot_demo_huisbieder.forms.GemeenteForm;
import com.domain.fednot_demo_huisbieder.services.GemeenteService;
import com.domain.fednot_demo_huisbieder.services.PandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
public class IndexController {
    private final PandService pandService;
    private final GemeenteService gemeenteService;

    public IndexController(PandService pandService, GemeenteService gemeenteService) {
        this.pandService = pandService;
        this.gemeenteService = gemeenteService;
    }

    @GetMapping
    public ModelAndView index(@PageableDefault(size = 6) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Pand> panden = pandService.findAll(pageable);
        panden.forEach(pand -> pand.checkDatumVsNu());
        modelAndView.addObject("page", pandService.findAll(pageable));
        modelAndView.addObject(new GemeenteForm(null));
        return modelAndView;
    }
}
