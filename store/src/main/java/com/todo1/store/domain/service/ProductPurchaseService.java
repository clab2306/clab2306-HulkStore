package com.todo1.store.domain.service;

import java.util.List;
import java.util.Optional;

import com.todo1.store.domain.dto.ProductPurchaseDTO;

/**
 * 
 * @author Claudia Lopez
 *
 */
public interface ProductPurchaseService {

    ProductPurchaseDTO save(ProductPurchaseDTO productPurchaseDTO);

    List<ProductPurchaseDTO> findAll();

    Optional<ProductPurchaseDTO> findOne(Long id);

    void delete(Long id);
}
