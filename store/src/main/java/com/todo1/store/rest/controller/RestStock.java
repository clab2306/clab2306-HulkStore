package com.todo1.store.rest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo1.store.domain.dto.StockDTO;
import com.todo1.store.domain.service.StockService;
import com.todo1.store.rest.error.BadRequestAlertException;
import com.todo1.store.rest.util.HeaderUtil;
import com.todo1.store.rest.util.PaginationUtil;

/**
 * REST controller for managing Stock.
 */
@RestController
@RequestMapping("/api")
public class RestStock {

    private final Logger log = LoggerFactory.getLogger(RestStock.class);

    private static final String ENTITY_NAME = "stock";

    private final StockService stockService;

    public RestStock(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/stocks")
    public ResponseEntity<StockDTO> createStock(@Valid @RequestBody StockDTO stockDTO) throws URISyntaxException {
        log.debug("REST request to save Stock : {}", stockDTO);
        if (stockDTO.getId() != null) {
            throw new BadRequestAlertException("A new stock cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockDTO result = stockService.save(stockDTO);
        return ResponseEntity.created(new URI("/api/stocks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/stocks")
    public ResponseEntity<StockDTO> updateStock(@Valid @RequestBody StockDTO stockDTO) throws URISyntaxException {
        log.debug("REST request to update Stock : {}", stockDTO);
        if (stockDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StockDTO result = stockService.save(stockDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stockDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<StockDTO>> getAllStocks(Pageable pageable) {
        log.debug("REST request to get all Stocks");
        Page<StockDTO> page = stockService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stocks");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<StockDTO> getStock(@PathVariable Long id) {
        log.debug("REST request to get Stock : {}", id);
        Optional<StockDTO> stockDTO = stockService.findOne(id);
        return stockDTO.isPresent() ? ResponseEntity.ok().body(stockDTO.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/stocks/product/{productId}")
    public ResponseEntity<StockDTO> getStockByProductId(@PathVariable Long productId) {
        log.debug("REST request to get Stock : {}", productId);
        StockDTO stockDTO = stockService.findByProductId(productId);
        return ResponseEntity.ok().body(stockDTO);
    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        log.debug("REST request to delete Stock : {}", id);
        stockService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
