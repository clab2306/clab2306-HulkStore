package com.todo1.store.infrastructure.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo1.store.domain.dto.ProductSaleDTO;
import com.todo1.store.domain.service.ProductSaleService;
import com.todo1.store.infrastructure.entity.ProductSale;
import com.todo1.store.infrastructure.mapper.ProductSaleMapper;
import com.todo1.store.infrastructure.repository.ProductSaleRepository;

/**
 * Service Implementation for managing ProductSale.
 */
@Service
@Transactional
public class ProductSaleServiceImpl implements ProductSaleService {

    private final Logger log = LoggerFactory.getLogger(ProductSaleServiceImpl.class);

    private final ProductSaleRepository productSaleRepository;

    private final ProductSaleMapper productSaleMapper;

    public ProductSaleServiceImpl(ProductSaleRepository productSaleRepository, ProductSaleMapper productSaleMapper) {
        this.productSaleRepository = productSaleRepository;
        this.productSaleMapper = productSaleMapper;
    }

    /**
     * Guardar productSale.
     *
     * @param productSaleDTO entidad a guardar
     * @return entidad guardada
     */
    @Override
    public ProductSaleDTO save(ProductSaleDTO productSaleDTO) {
        log.debug("Solicitud para guardar ProductSale : {}", productSaleDTO);
        ProductSale productSale = productSaleMapper.productSaleDTOToProductSale(productSaleDTO);
        productSale = productSaleRepository.save(productSale);
        return productSaleMapper.productSaleToProductSaleDTO(productSale);
    }

    /**
     * Obtener todos los productSales.
     *
     * @return Lista de entidades
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductSaleDTO> findAll() {
        log.debug("Solicitud para obtener todos los ProductSales");
        return productSaleMapper.productsSaleToProductSaleDTOs(productSaleRepository.findAll());
    }


    /**
     * Obtiene un productSale por id.
     *
     * @param id de la entidad ProductSale
     * @return entidad ProductSale
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductSaleDTO> findOne(Long id) {
        log.debug("Solicitud para obtener ProductSale : {}", id);
        return productSaleRepository.findById(id)
            .map(productSaleMapper::productSaleToProductSaleDTO);
    }

    /**
     * Eliminacion de productSale por id.
     *
     * @param id de la entidad ProductSale
     */
    @Override
    public void delete(Long id) {
        log.debug("Solicitud para eliminar ProductSale : {}", id);
        productSaleRepository.deleteById(id);
    }
}
