package com.todo1.store.util.builder;

import com.todo1.store.model.entity.Product;
import com.todo1.store.model.entity.ProductSale;
import com.todo1.store.model.entity.Sale;

public class ProductSaleBuilder {

    private Long id;
    private Long amount;
    private Product product;
    private Sale sale;

    public ProductSaleBuilder() {
        this.id = 1L;
        this.amount = 10L;
        this.product = ProductBuilder.aProduct().build();
        this.sale = new Sale();
        this.sale.setId(1L);
    }

    public ProductSale build() {
        ProductSale productSale = new ProductSale();
        productSale.setId(id);
        productSale.setAmount(amount);
        productSale.setProduct(product);
        productSale.setSale(sale);
        return productSale;
    }

    public static ProductSaleBuilder aProductSale() {
        return new ProductSaleBuilder();
    }

    public ProductSaleBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductSaleBuilder withAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public ProductSaleBuilder withProduct(Product product) {
        this.product = product;
        return this;
    }
    
    public ProductSaleBuilder withSale(Sale sale) {
        this.sale = sale;
        return this;
    }
}
