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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo1.store.domain.dto.ProductDTO;
import com.todo1.store.domain.service.ProductService;
import com.todo1.store.rest.error.BadRequestAlertException;
import com.todo1.store.rest.util.HeaderUtil;
import com.todo1.store.rest.util.PaginationUtil;

/**
 * REST controller para gestionar Producto.
 */
@RestController
@RequestMapping("/api")
public class RestProduct {

    private final Logger log = LoggerFactory.getLogger(RestProduct.class);

    private static final String ENTITY_NAME = "product";

    private final ProductService productService;

    public RestProduct(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) throws URISyntaxException {
        log.debug("REST solicitud para crear producto : {}", productDTO);
        if (productDTO.getId() != null) {
            throw new BadRequestAlertException("Un nuevo producto no puede tener Id", ENTITY_NAME, "id existe");
        }
        ProductDTO result = productService.save(productDTO);
        return ResponseEntity.created(new URI("/api/products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/products")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO) {
        log.debug("REST solicitud para actualizar producto : {}", productDTO);
        if (productDTO.getId() == null) {
            throw new BadRequestAlertException("id invalido", ENTITY_NAME, "id null");
        }
        ProductDTO result = productService.save(productDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(Pageable pageable) {
        final Page<ProductDTO> page = productService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        log.debug("REST solicitud para obtener producto : {}", id);
        Optional<ProductDTO> productDTO = productService.findOne(id);
        	
        return productDTO.isPresent() ? ResponseEntity.ok().body(productDTO.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("REST solicitud eliminar obtener producto : {}", id);
        productService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
