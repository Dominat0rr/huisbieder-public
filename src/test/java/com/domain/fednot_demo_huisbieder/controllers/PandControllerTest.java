package com.domain.fednot_demo_huisbieder.controllers;

import com.domain.fednot_demo_huisbieder.services.GebruikerService;
import com.domain.fednot_demo_huisbieder.services.PandService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class PandControllerTest {
    private PandController pandController;
    @Mock
    private PandService pandService;
    @Mock
    private GebruikerService gebruikerService;
    @Mock
    private Errors errors;

    @Before
    public void before() {
        pandController = new PandController(pandService, gebruikerService);
    }

    @Test
    public void pandGebruiktDeThymeleafPaginaPand() {
        assertThat(pandController.pand(1).getViewName()).isEqualTo("pand");
    }

    @Test
    public void pandGebruiktDeThymeleafPaginaPandOokBijNietGevondenPand() {
        assertThat(pandController.pand(-1).getViewName()).isEqualTo("pand");
    }
}
