package com.todo1.store.util.builder;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.todo1.store.model.entity.ProductSale;
import com.todo1.store.model.entity.Sale;
import com.todo1.store.model.entity.User;

public class SaleBuilder {

    private Long id;
    private Long total;
    private Instant saleDate;
    private User user;
    private Set<ProductSale> productSales = new HashSet<>();

    public SaleBuilder() {
        this.id = 1L;
        this.total = 1000L;
        this.saleDate = Instant.ofEpochMilli(0L);
        this.user = new User();
        this.productSales.add(ProductSaleBuilder.aProductSale().build());
    }

    public Sale build() {
        Sale sale = new Sale();
        sale.setId(id);
        sale.setTotal(total);
        sale.setSaleDate(saleDate);
        sale.setUser(user);
        sale.setProductSales(productSales);
        return sale;
    }

    public static SaleBuilder aSale() {
        return new SaleBuilder();
    }

    public SaleBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SaleBuilder withTotal(Long total) {
        this.total = total;
        return this;
    }

    public SaleBuilder withSaleDate(Instant saleDate) {
        this.saleDate = saleDate;
        return this;
    }

    public SaleBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public SaleBuilder withProductSales(Set<ProductSale> productSales) {
        this.productSales = productSales;
        return this;
    }
}
