package com.todo1.store.domain.dto;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author Claudia Lopez
 *
 */
public class SaleDTO {

    private Long id;

    @NotNull
    private Long total;

    @NotNull
    private Instant saleDate;

    private Long userId;

    private Set<ProductSaleDTO> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Instant getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Instant saleDate) {
        this.saleDate = saleDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<ProductSaleDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductSaleDTO> products) {
        this.products = products;
    }

}
