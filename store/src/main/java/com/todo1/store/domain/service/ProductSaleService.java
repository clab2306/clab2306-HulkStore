package com.todo1.store.domain.service;

import java.util.List;
import java.util.Optional;

import com.todo1.store.domain.dto.ProductSaleDTO;

/**
 * 
 * @author Claudia Lopez
 *
 */
public interface ProductSaleService {


    ProductSaleDTO save(ProductSaleDTO productSaleDTO);

    List<ProductSaleDTO> findAll();

    Optional<ProductSaleDTO> findOne(Long id);

    void delete(Long id);
}
