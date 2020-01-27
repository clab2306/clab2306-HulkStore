package com.todo1.store.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo1.store.model.entity.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

}
