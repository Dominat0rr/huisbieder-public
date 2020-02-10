package com.domain.fednot_demo_huisbieder.controllers;

import com.domain.fednot_demo_huisbieder.services.GebruikerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
    private LoginController loginController;
    @Mock
    private GebruikerService gebruikerService;

    @Before
    public void before() {
        loginController = new LoginController(gebruikerService);
    }

    @Test
    public void loginGebruiktDeThymeleafPaginaLogin() {
        assertThat(loginController.login().getViewName()).isEqualTo("login");
    }
}
