package com.todo1.store.domain.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo1.store.domain.dto.ProductTypeDTO;
import com.todo1.store.domain.service.ProductTypeService;
import com.todo1.store.model.entity.ProductType;
import com.todo1.store.model.mapper.ProductTypeMapper;
import com.todo1.store.model.repository.ProductTypeRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ProductType.
 */
@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {

    private final Logger log = LoggerFactory.getLogger(ProductTypeServiceImpl.class);

    private final ProductTypeRepository productTypeRepository;

    private final ProductTypeMapper productTypeMapper;

    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository, ProductTypeMapper productTypeMapper) {
        this.productTypeRepository = productTypeRepository;
        this.productTypeMapper = productTypeMapper;
    }

    /**
     * Guardar productType.
     *
     * @param productTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProductTypeDTO save(ProductTypeDTO productTypeDTO) {
        log.debug("Solicitud para obtener ProductType : {}", productTypeDTO);
        ProductType productType = productTypeMapper.productTypeDTOToProductType(productTypeDTO);
        productType = productTypeRepository.save(productType);
        return productTypeMapper.productTypeToProductTypeDTO(productType);
    }

    /**
     * Obtener todos productTypes.
     *
     * @return Lista de ProductTypeDTO
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductTypeDTO> findAll() {
        log.debug("Solicitud para obtener todos los ProductTypes");
        return productTypeRepository.findAll().stream()
            .map(productTypeMapper::productTypeToProductTypeDTO)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Obtener un productType por id.
     *
     * @param id de ProductTypeDTO
     * @return ProductTypeDTO
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductTypeDTO> findOne(Long id) {
        log.debug("Solicitud para obtener ProductType : {}", id);
        return productTypeRepository.findById(id)
            .map(productTypeMapper::productTypeToProductTypeDTO);
    }

    /**
     * Eliminar productType por id id.
     *
     * @param id id de ProductType
     */
    @Override
    public void delete(Long id) {
        log.debug("Solicitud para eliminar ProductType : {}", id);
        productTypeRepository.deleteById(id);
    }

    
}
