package com.todo1.store.domain.service;


import java.util.List;
import java.util.Optional;

import com.todo1.store.domain.dto.ProductTypeDTO;

/**
 * 
 * @author Claudia Lopez
 *
 */
public interface ProductTypeService {


    ProductTypeDTO save(ProductTypeDTO productTypeDTO);

    List<ProductTypeDTO> findAll();

    Optional<ProductTypeDTO> findOne(Long id);

    void delete(Long id);
}
