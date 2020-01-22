package com.todo1.store.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.todo1.store.infrastructure.entity.ProductPurchase;

@Repository
public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase, Long>, JpaSpecificationExecutor<ProductPurchase> {

}
