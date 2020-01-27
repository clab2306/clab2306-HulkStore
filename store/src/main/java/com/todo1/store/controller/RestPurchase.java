package com.todo1.store.controller;

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

import com.todo1.store.domain.dto.PurchaseDTO;
import com.todo1.store.domain.service.PurchaseService;
import com.todo1.store.error.BadRequestAlertException;
import com.todo1.store.util.HeaderUtil;


@RestController
@RequestMapping("/api")
public class RestPurchase {

    private final Logger log = LoggerFactory.getLogger(RestPurchase.class);

    private static final String ENTITY_NAME = "purchase";

    private final PurchaseService purchaseService;

    public RestPurchase(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
        
    }

    @PostMapping("/purchases")
    public ResponseEntity<PurchaseDTO> createPurchase(@Valid @RequestBody PurchaseDTO purchaseDTO) throws URISyntaxException {
        log.debug("REST request to save Purchase : {}", purchaseDTO);
        if (purchaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new purchase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseDTO result = purchaseService.save(purchaseDTO);
        return ResponseEntity.created(new URI("/api/purchases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/purchases")
    public ResponseEntity<PurchaseDTO> updatePurchase(@Valid @RequestBody PurchaseDTO purchaseDTO) throws URISyntaxException {
        log.debug("REST request to update Purchase : {}", purchaseDTO);
        if (purchaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PurchaseDTO result = purchaseService.save(purchaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, purchaseDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<PurchaseDTO> getPurchase(@PathVariable Long id) {
        log.debug("REST request to get Purchase : {}", id);
        Optional<PurchaseDTO> purchaseDTO = purchaseService.findOne(id);
        return purchaseDTO.isPresent() ? ResponseEntity.ok().body(purchaseDTO.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/purchases/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
        log.debug("REST request to delete Purchase : {}", id);
        purchaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
