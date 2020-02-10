package com.domain.fednot_demo_huisbieder.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            return new ModelAndView("error", "errorcode", statusCode);
        }
        return new ModelAndView("error");
    }

    @Override
    public String getErrorPath() {
        return "/errors/error";
    }
}
