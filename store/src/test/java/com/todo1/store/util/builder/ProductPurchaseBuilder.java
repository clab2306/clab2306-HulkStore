package com.todo1.store.util.builder;

import com.todo1.store.infrastructure.entity.Product;
import com.todo1.store.infrastructure.entity.ProductPurchase;
import com.todo1.store.infrastructure.entity.Purchase;

public class ProductPurchaseBuilder {

    private Long id;
    private Long amount;
    private Product product;
    private Purchase purchase;

    public ProductPurchaseBuilder() {
        this.id = 1L;
        this.amount = 10L;
        this.product = ProductBuilder.aProduct().build();
        this.purchase = new Purchase();
        this.purchase.setId(1L);
    }

    public ProductPurchase build() {
        ProductPurchase productSale = new ProductPurchase();
        productSale.setId(id);
        productSale.setAmount(amount);
        productSale.setProduct(product);
        productSale.setPurchase(purchase);
        return productSale;
    }

    public static ProductPurchaseBuilder aProductPurchase() {
        return new ProductPurchaseBuilder();
    }

    public ProductPurchaseBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductPurchaseBuilder withAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public ProductPurchaseBuilder withProduct(Product product) {
        this.product = product;
        return this;
    }

    public ProductPurchaseBuilder withPurchase(Purchase purchase) {
        this.purchase = purchase;
        return this;
    }
}
