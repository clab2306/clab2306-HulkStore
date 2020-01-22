package com.todo1.store.domain.service;



import java.util.List;
import java.util.Optional;

import com.todo1.store.domain.dto.PurchaseDTO;

/**
 * 
 * @author Claudia Lopez
 *
 */
public interface PurchaseService {

    PurchaseDTO save(PurchaseDTO purchaseDTO);

    List<PurchaseDTO> findAll();

    Optional<PurchaseDTO> findOne(Long id);

    void delete(Long id);
}
