package com.domain.fednot_demo_huisbieder.controllers;

import com.domain.fednot_demo_huisbieder.entities.Gemeente;
import com.domain.fednot_demo_huisbieder.entities.Pand;
import com.domain.fednot_demo_huisbieder.forms.PandToevoegenForm;
import com.domain.fednot_demo_huisbieder.services.GemeenteService;
import com.domain.fednot_demo_huisbieder.services.PandService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping(path = "toevoegen", produces = MediaType.TEXT_HTML_VALUE)
public class PandToevoegenController {
    private final PandService pandService;
    private final GemeenteService gemeenteService;

    public PandToevoegenController(PandService pandService, GemeenteService gemeenteService) {
        this.pandService = pandService;
        this.gemeenteService = gemeenteService;
    }

    @GetMapping()
    public ModelAndView toevoegen() {
        ModelAndView modelAndView = new ModelAndView("toevoegen")
                .addObject(new PandToevoegenForm(null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null));
        return modelAndView;
    }

    @PostMapping("/voegtoe")
    public ModelAndView voegtoe(@Valid @Validated PandToevoegenForm form, Errors errors, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("toevoegen");
        if (errors.hasErrors()) {
            System.out.println("ERRORS: " + errors.toString());
            System.out.println("TEST");
            return modelAndView;
        }
        Optional<Gemeente> gemeente = gemeenteService.findByPostcode(form.getPostcode());
        Pand pand;
        if (!gemeente.isPresent()) {
            Gemeente nieuweGemeente = new Gemeente(form.getPostcode(), form.getGemeente());
            gemeenteService.create(nieuweGemeente);
            pand = new Pand(form.getTypeWoning(), form.getNaam(), form.getTitel(), form.getStraatnaam(), form.getHuisnummer(), nieuweGemeente,
                    form.getBouwjaar(), form.getOppervlakte(), form.getStaatgebouw(), form.getAantalgevels(), form.getAantalslaapkamers(), form.getAantalbadkamers(),
                    form.getAantaltoiletten(), form.getAantalverdiepingen(), form.getLiving(), form.getEetkamer(), form.getBureau(), form.getKelder(), form.getZolder(),
                    form.getGarage(), form.getTuin(), form.getTerras(), form.getVeranda(), form.getCode_epc(), form.getDubbelebegelazing(), form.getKadastraalinkomen(),
                    form.getVrijVanaf(), form.getStartbod(), form.getStartdatum(), form.getEinddatum(), form.getOmschrijving());
        } else {
            pand = new Pand(form.getTypeWoning(), form.getNaam(), form.getTitel(), form.getStraatnaam(), form.getHuisnummer(), gemeente.get(),
                    form.getBouwjaar(), form.getOppervlakte(), form.getStaatgebouw(), form.getAantalgevels(), form.getAantalslaapkamers(), form.getAantalbadkamers(),
                    form.getAantaltoiletten(), form.getAantalverdiepingen(), form.getLiving(), form.getEetkamer(), form.getBureau(), form.getKelder(), form.getZolder(),
                    form.getGarage(), form.getTuin(), form.getTerras(), form.getVeranda(), form.getCode_epc(), form.getDubbelebegelazing(), form.getKadastraalinkomen(),
                    form.getVrijVanaf(), form.getStartbod(), form.getStartdatum(), form.getEinddatum(), form.getOmschrijving());
        }
        String pandURL = request.getRequestURL().toString().replace("toevoegen","").replace("/voegtoe", "panden");
        pandService.create(pand, pandURL);
        return new ModelAndView("redirect:/toevoegen/toegevoegd");
    }

    @GetMapping("/toegevoegd")
    public ModelAndView toegevoegd() {
        return new ModelAndView("toegevoegd");
    }
}
