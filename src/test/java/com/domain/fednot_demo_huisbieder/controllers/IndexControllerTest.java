package com.domain.fednot_demo_huisbieder.controllers;

import com.domain.fednot_demo_huisbieder.entities.Gemeente;
import com.domain.fednot_demo_huisbieder.entities.Pand;
import com.domain.fednot_demo_huisbieder.entities.PandType;
import com.domain.fednot_demo_huisbieder.services.GemeenteService;
import com.domain.fednot_demo_huisbieder.services.PandService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;;

import java.time.LocalDateTime;
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
public class IndexControllerTest {
    private IndexController indexController;
    @Mock
    private PandService pandService;
    @Mock
    private GemeenteService gemeenteService;
    @Mock
    private Pageable pageable;

    @Before
    public void before() {
        Gemeente gemeente = new Gemeente("3010", "Kessel-Lo");
        when(pandService.findById(1))
                .thenReturn(Optional.of(new Pand(PandType.HUIS, "test1", "test1", "testStraat1", "11", gemeente, 1999, 550,
                        null, (byte)3, (byte)3, (byte)1, (byte)2, (byte)3, true, true, null,
                        true, true, null, true, true, null, null, null, 750,
                        "Bij de akte", 250_000, LocalDateTime.now(), LocalDateTime.now().plusDays(7), "Mooie woning")));
        indexController = new IndexController(pandService, gemeenteService);
    }

    // Panden
    @Test
    public void indexGebruiktDeThymeleafPaginaIndex() {
        assertThat(indexController.index(pageable).getViewName()).isEqualTo("index");
    }

    @Test
    public void indexGeeftPandenDoorAanDeThymeleafPagina() {
        assertThat(indexController.index(pageable).getModel().get("panden")).isInstanceOf(List.class);
    }
}
