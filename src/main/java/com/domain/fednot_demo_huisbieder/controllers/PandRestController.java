package com.domain.fednot_demo_huisbieder.controllers;

import com.domain.fednot_demo_huisbieder.entities.Pand;
import com.domain.fednot_demo_huisbieder.exceptions.PandNietGevondenException;
import com.domain.fednot_demo_huisbieder.services.PandService;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RestController
@RequestMapping("/panden")
@ExposesResourceFor(Pand.class)
@CrossOrigin("http://localhost:4200")
public class PandRestController {
    private final PandService pandService;
    private final EntityLinks entityLinks;

    public PandRestController(PandService pandService, EntityLinks entityLinks) {
        this.pandService = pandService;
        this.entityLinks = entityLinks;
    }

    // Get
    @GetMapping()
    public List<Pand> findAll() {
        return pandService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pand> get(@PathVariable(value = "id") Long pandId)
            throws PandNietGevondenException {
        Pand pand = pandService.findById(pandId)
                .orElseThrow(() -> new PandNietGevondenException());
        return ResponseEntity.ok().body(pand);
    }

    // Create
    @PostMapping("/{id}")
    public void create(@Valid @RequestBody Pand pand) {
        String pandURL = "http://localhost:8080/panden/";
        pandService.create(pand, pandURL);
    }

    // Update
    @PutMapping("{id}")
    public void update(@RequestBody @Valid Pand pand) {
        pandService.update(pand);
    }
}
