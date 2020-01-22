package com.todo1.store.domain.service;

import java.util.List;
import java.util.Optional;
import com.todo1.store.domain.dto.SaleDTO;

/**
 * 
 * @author Claudia Lopez
 *
 */
public interface SaleService {

    SaleDTO save(SaleDTO saleDTO);

    List<SaleDTO> findAll();

    Optional<SaleDTO> findOne(Long id);

    void delete(Long id);
}
