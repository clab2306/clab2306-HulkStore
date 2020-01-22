package com.todo1.store.rest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo1.store.domain.dto.SaleDTO;
import com.todo1.store.domain.service.SaleService;
import com.todo1.store.rest.error.BadRequestAlertException;
import com.todo1.store.rest.util.HeaderUtil;

/**
 * REST controller for managing Sale.
 */
@RestController
@RequestMapping("/api")
public class RestSale {

    private final Logger log = LoggerFactory.getLogger(RestSale.class);

    private static final String ENTITY_NAME = "sale";

    private final SaleService saleService;

    public RestSale(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/sales")
    public ResponseEntity<SaleDTO> createSale(@Valid @RequestBody SaleDTO saleDTO) throws URISyntaxException {
        log.debug("REST request to save Sale : {}", saleDTO);
        if (saleDTO.getId() != null) {
            throw new BadRequestAlertException("A new sale cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SaleDTO result = saleService.save(saleDTO);
        return ResponseEntity.created(new URI("/api/sales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/sales")
    public ResponseEntity<SaleDTO> updateSale(@Valid @RequestBody SaleDTO saleDTO) {
        log.debug("REST request to update Sale : {}", saleDTO);
        if (saleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SaleDTO result = saleService.save(saleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, saleDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<SaleDTO> getSale(@PathVariable Long id) {
        log.debug("REST request to get Sale : {}", id);
        Optional<SaleDTO> saleDTO = saleService.findOne(id);
        return saleDTO.isPresent() ? ResponseEntity.ok().body(saleDTO.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/sales/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        log.debug("REST request to delete Sale : {}", id);
        saleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
