package com.domain.fednot_demo_huisbieder.controllers;

import com.domain.fednot_demo_huisbieder.services.GebruikerService;
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
@RequestMapping(path = "login", produces = MediaType.TEXT_HTML_VALUE)
public class LoginController {
    private final GebruikerService gebruikerService;

    public LoginController(GebruikerService gebruikerService) {
        this.gebruikerService = gebruikerService;
    }

    @GetMapping
    public ModelAndView login(){
        return new ModelAndView("login");
    }
}
