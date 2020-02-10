package com.domain.fednot_demo_huisbieder.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class GemeenteForm {
    @NotNull
    @NotBlank
    @Size(min = 2, message = "Moet minstens 2 karakters lang zijn")
    private String zoekTerm; // kan gemeente of postcode zijn;

    public GemeenteForm(String zoekTerm) {
        this.zoekTerm = zoekTerm;
    }

    public String getZoekTerm() {
        return zoekTerm;
    }
}
