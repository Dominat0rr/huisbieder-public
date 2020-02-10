package com.domain.fednot_demo_huisbieder.forms;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class BodForm {
    @NotNull()
    @Positive()
    @Range(min = 1, max = 999999999, message = "Bod moet tussen 1 en 999.999.999 liggen")
    private Integer bod;

    public BodForm(Integer bod) {
        this.bod = bod;
    }

    public Integer getBod() {
        return bod;
    }
}

