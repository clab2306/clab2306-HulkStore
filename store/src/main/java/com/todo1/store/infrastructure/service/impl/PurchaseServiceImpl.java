package com.todo1.store.infrastructure.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo1.store.domain.dto.PurchaseDTO;
import com.todo1.store.domain.service.PurchaseService;
import com.todo1.store.infrastructure.entity.Purchase;
import com.todo1.store.infrastructure.mapper.PurchaseMapper;
import com.todo1.store.infrastructure.repository.PurchaseRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Purchase.
 */
@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    private final Logger log = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    private final PurchaseRepository purchaseRepository;

    private final PurchaseMapper purchaseMapper;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, PurchaseMapper purchaseMapper) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseMapper = purchaseMapper;
    }

    /**
     * Guardar purchase.
     *
     * @param purchaseDTO entidad a guardar
     * @return Entidad guardada
     */
    @Override
    public PurchaseDTO save(PurchaseDTO purchaseDTO) {
        log.debug("Solicitud para guardad Purchase : {}", purchaseDTO);
        Purchase purchase = purchaseMapper.purchaseDTOToPurchase(purchaseDTO);
        purchase = purchaseRepository.save(purchase);
        return purchaseMapper.purchaseToPurchaseDTO(purchase);
    }

    /**
     * Obtenet todos purchases.
     *
     * @return Lista de Purchase
     */
    @Override
    @Transactional(readOnly = true)
    public List<PurchaseDTO> findAll() {
        log.debug("Request to get all Purchases");
        return purchaseRepository.findAll().stream()
            .map(purchaseMapper::purchaseToPurchaseDTO)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Obtener un purchase por id.
     *
     * @param id id de Purchase
     * @return Purchase
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PurchaseDTO> findOne(Long id) {
        log.debug("Solicitud para obtenet Purchase : {}", id);
        return purchaseRepository.findById(id)
            .map(purchaseMapper::purchaseToPurchaseDTO);
    }

    /**
     * Eliminar purchase por id.
     *
     * @param id id de Purchase
     */
    @Override
    public void delete(Long id) {
        log.debug("Solicitud para eliminar Purchase : {}", id);
        purchaseRepository.deleteById(id);
    }
}
