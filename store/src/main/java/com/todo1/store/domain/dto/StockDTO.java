package com.todo1.store.domain.dto;

import java.time.Instant;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author Claudia Lopez
 *
 */
public class StockDTO {

    private Long id;

    @NotNull
    private Long amount;

    @NotNull
    private Instant updateDate;

    private Long productId;

    private Long updateUserId;

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

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long userId) {
        this.updateUserId = userId;
    }

}
