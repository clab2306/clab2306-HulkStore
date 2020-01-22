package com.todo1.store.infrastructure.service.impl;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo1.store.domain.dto.ProductPurchaseDTO;
import com.todo1.store.domain.service.ProductPurchaseService;
import com.todo1.store.infrastructure.entity.ProductPurchase;
import com.todo1.store.infrastructure.mapper.ProductPurchaseMapper;
import com.todo1.store.infrastructure.repository.ProductPurchaseRepository;

/**
 * Implementacion Service para manejp de  ProductPurchase.
 */
@Service
@Transactional
public class ProductPurchaseServiceImpl implements ProductPurchaseService {

    private final Logger log = LoggerFactory.getLogger(ProductPurchaseServiceImpl.class);

    private final ProductPurchaseRepository productPurchaseRepository;

    private final ProductPurchaseMapper productPurchaseMapper;

    public ProductPurchaseServiceImpl(ProductPurchaseRepository productPurchaseRepository, ProductPurchaseMapper productPurchaseMapper) {
        this.productPurchaseRepository = productPurchaseRepository;
        this.productPurchaseMapper = productPurchaseMapper;
    }

    /**
     * Guardar un productPurchase.
     *
     * @param productPurchaseDTO entidad a guardar
     * @return entidad guardada
     */
    @Override
    public ProductPurchaseDTO save(ProductPurchaseDTO productPurchaseDTO) {
        log.debug("solicitud para guardar ProductPurchase : {}", productPurchaseDTO);
        ProductPurchase productPurchase = productPurchaseMapper.productPurchaseDTOToProductPurchase(productPurchaseDTO);
        productPurchase = productPurchaseRepository.save(productPurchase);
        return productPurchaseMapper.productPurchaseToProductPurchaseDTO(productPurchase);
    }

    /**
     * Obtener todos los productPurchases.
     *
     * @return Lista de entidades
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductPurchaseDTO> findAll() {
        log.debug("Solicitud para obtener todos ProductPurchases");
        
        return productPurchaseMapper.productsPurchaseToProductPurchaseDTOs(productPurchaseRepository.findAll());
    }


    /**
     * Obtener un productPurchase por id.
     *
     * @param id Id de la entidad
     * @return la entidad
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductPurchaseDTO> findOne(Long id) {
        log.debug("Solicitud para obtener ProductPurchase : {}", id);
        return productPurchaseRepository.findById(id)
            .map(productPurchaseMapper::productPurchaseToProductPurchaseDTO);
    }

    /**
     * Eliminar productPurchase por id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Solicitud para obtener borrar ProductPurchase : {}", id);
        productPurchaseRepository.deleteById(id);
    }
}
