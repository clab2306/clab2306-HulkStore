package com.todo1.store.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.todo1.store.domain.dto.StockDTO;

import java.util.Optional;

/**
 * Service Interface for managing Stock.
 */
public interface StockService {

    StockDTO save(StockDTO stockDTO);

    Page<StockDTO> findAll(Pageable pageable);

    Optional<StockDTO> findOne(Long id);

    StockDTO findByProductId(Long productId);
    
    void delete(Long id);
}
