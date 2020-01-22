package com.todo1.store.domain.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author Claudia Lopez
 *
 */
public class ProductPurchaseDTO {

    private Long id;

    @NotNull
    private Long amount;

    private Long productId;

    private Long purchaseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }
}
