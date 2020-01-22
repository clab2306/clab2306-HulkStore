package com.todo1.store.domain.service;

import java.util.List;
import java.util.Optional;

import com.todo1.store.domain.dto.TypeDTO;

/**
 * 
 * @author Claudia Lopez
 *
 */
public interface TypeService {

    TypeDTO save(TypeDTO typeDTO);

    List<TypeDTO> findAll();

    Optional<TypeDTO> findOne(Long id);

    void delete(Long id);
}
