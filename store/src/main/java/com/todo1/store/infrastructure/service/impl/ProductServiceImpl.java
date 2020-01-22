package com.todo1.store.infrastructure.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo1.store.domain.dto.ProductDTO;
import com.todo1.store.domain.service.ProductService;
import com.todo1.store.infrastructure.entity.Product;
import com.todo1.store.infrastructure.mapper.ProductMapper;
import com.todo1.store.infrastructure.repository.ProductRepository;

/**
 *  Implementacion de Service para manejar productos.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    /**
     * Guardar productos
     *
     * @param productDTO la entidad a salvar
     * @return Entidad guardada
     */
    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Solicitud para guardar un producto : {}", productDTO);
        Product product = productMapper.productDTOToProduct(productDTO);
        product = productRepository.save(product);
        return productMapper.productToProductDTO(product);
    }

    /**
     * Obtiene todos los productos
     *
     * @param pageable informacion de la paginaciòn
     * @return Lista de entidades
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        log.debug("Solicitud para obtener todos los productos");
        return productRepository.findAll(pageable)
            .map(productMapper::productToProductDTO);
    }


    /**
     * Obtiene un producto por id.
     *
     * @param id Id del producto
     * @return Entidad
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findOne(Long id) {
        log.debug("Solicitud para obtener un producto : {}", id);
        return productRepository.findById(id)
            .map(productMapper::productToProductDTO);
    }

    /**
     * Eliminacion de un producto por id
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Solicitud para eliminar producto : {}", id);
        productRepository.deleteById(id);
    }
}
