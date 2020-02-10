package com.domain.fednot_demo_huisbieder.controllers;

import com.domain.fednot_demo_huisbieder.entities.Gemeente;
import com.domain.fednot_demo_huisbieder.exceptions.GemeenteNietGevondenException;
import com.domain.fednot_demo_huisbieder.services.GemeenteService;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RestController
@RequestMapping("/gemeenten")
@ExposesResourceFor(Gemeente.class)
public class GemeenteRestController {
    private final GemeenteService gemeenteService;
    private final EntityLinks entityLinks;

    public GemeenteRestController(GemeenteService gemeenteService, EntityLinks entityLinks) {
        this.gemeenteService = gemeenteService;
        this.entityLinks = entityLinks;
    }

    // Get
    @GetMapping("/{id}")
    public ResponseEntity<Gemeente> get(@PathVariable(value = "id") Long gemeenteId)
            throws GemeenteNietGevondenException {
        Gemeente gemeente = gemeenteService.findById(gemeenteId)
                .orElseThrow(() -> new GemeenteNietGevondenException());
        return ResponseEntity.ok().body(gemeente);
    }

    // Create
    @PostMapping("/{id}")
    public void create(@Valid @RequestBody Gemeente gemeente) {
        gemeenteService.create(gemeente);
    }
}
