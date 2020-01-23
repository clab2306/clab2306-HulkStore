package com.todo1.store.domain.dto;

import java.time.Instant;
import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author Claudia Lopez
 *
 */
public class PurchaseDTO {

    private Long id;

    @NotNull
    private Long total;

    @NotNull
    private Instant purchaseDate;

    private Set<ProductPurchaseDTO> productPurchases;

    private Long userId;

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

    public Instant getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Instant purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<ProductPurchaseDTO> getProductPurchases() {
        return productPurchases;
    }

    public void setProductPurchases(Set<ProductPurchaseDTO> products) {
        this.productPurchases = products;
    }


}
