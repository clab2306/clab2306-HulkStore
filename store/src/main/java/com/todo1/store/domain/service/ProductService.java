package com.todo1.store.domain.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.todo1.store.domain.dto.ProductDTO;

import java.util.Optional;

public interface ProductService {

    ProductDTO save(ProductDTO productDTO);

    Page<ProductDTO> findAll(Pageable pageable);

    Optional<ProductDTO> findOne(Long id);

    void delete(Long id);
}
