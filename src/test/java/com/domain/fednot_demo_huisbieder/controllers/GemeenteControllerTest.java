package com.domain.fednot_demo_huisbieder.controllers;

import com.domain.fednot_demo_huisbieder.entities.Gemeente;
import com.domain.fednot_demo_huisbieder.forms.GemeenteForm;
import com.domain.fednot_demo_huisbieder.services.GemeenteService;
import com.domain.fednot_demo_huisbieder.services.PandService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class GemeenteControllerTest {
    private GemeenteController gemeenteController;
    @Mock
    private GemeenteService gemeenteService;
    @Mock
    private PandService pandService;
    @Mock
    private Errors errors;

    @Before
    public void before() {
        when(gemeenteService.findById(1)).thenReturn(Optional.of(new Gemeente("3010", "Kessel-Lo")));
        gemeenteController = new GemeenteController(gemeenteService, pandService);
    }

    @Test
    public void gemeenteZoekTermGebruiktRedirectNaarGemeentenPanden() {
        assertThat(gemeenteController.gemeenteZoekTerm(new GemeenteForm("Kessel-Lo"), errors).getViewName()).isEqualTo("redirect:/gemeenten/panden");
    }

    @Test
    public void gevondenPandenGeeftPandenDoorAanDeThymeleafPagina() {
        assertThat(gemeenteController.gevondenPanden().getModel().get("panden")).isInstanceOf(List.class);
    }
}
