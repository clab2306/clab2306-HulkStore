package com.todo1.store.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.todo1.store.infrastructure.persistence.entity.ProductSale;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long>, JpaSpecificationExecutor<ProductSale> {

}
