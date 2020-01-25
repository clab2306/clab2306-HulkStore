package com.todo1.store.util.builder;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.todo1.store.infrastructure.entity.ProductPurchase;
import com.todo1.store.infrastructure.entity.Purchase;
import com.todo1.store.infrastructure.entity.User;

public class PurchaseBuilder {

    private Long id;
    private Long total;
    private Instant purchaseDate;
    private User user;
    private Set<ProductPurchase> productPurchases = new HashSet<>();

    public PurchaseBuilder() {
        this.id = 1L;
        this.total = 1000L;
        this.purchaseDate = Instant.ofEpochMilli(0L);
        this.user = new User();
        this.productPurchases.add(ProductPurchaseBuilder.aProductPurchase().build());
    }

    public Purchase build() {
        Purchase purchase = new Purchase();
        purchase.setId(id);
        purchase.setTotal(total);
        purchase.setPurchaseDate(purchaseDate);
        purchase.setUser(user);
        purchase.setProductPurchases(productPurchases);
        return purchase;
    }

    public static PurchaseBuilder aPurchase() {
        return new PurchaseBuilder();
    }

    public PurchaseBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PurchaseBuilder withTotal(Long total) {
        this.total = total;
        return this;
    }

    public PurchaseBuilder withSaleDate(Instant purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public PurchaseBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public PurchaseBuilder withProductSales(Set<ProductPurchase> productSales) {
        this.productPurchases = productSales;
        return this;
    }
}
