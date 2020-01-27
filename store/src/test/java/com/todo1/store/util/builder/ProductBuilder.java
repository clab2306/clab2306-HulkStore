package com.todo1.store.util.builder;

import java.time.Instant;

import com.todo1.store.model.entity.Product;

public class ProductBuilder {

    private Long id;
    private String description;
    private Long price;
    private Instant dateUpdate;

    public ProductBuilder() {
        this.id = 1L;
        this.description = "camiseta";
        this.price = 5000L;
        this.dateUpdate = Instant.ofEpochMilli(0L);
    }

    public static ProductBuilder aProduct() {
        return new ProductBuilder();
    }

    public Product build() {
        Product product = new Product();
        product.setId(id);
        product.setDescription(description);
        product.setPrice(price);
        product.dateUpdate(dateUpdate);
        return product;
    }

    public ProductBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder withPrice(Long price) {
        this.price = price;
        return this;
    }

    public ProductBuilder withDateUpdate(Instant dateUpdate) {
        this.dateUpdate = dateUpdate;
        return this;
    }
}
