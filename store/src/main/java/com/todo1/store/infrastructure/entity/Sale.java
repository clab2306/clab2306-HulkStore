package com.todo1.store.infrastructure.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Claudia Lopez
 *
 */
@Entity
@Table(name = "sale")
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "total", nullable = false)
    private Long total;

    @NotNull
    @Column(name = "sale_date", nullable = false)
    private Instant saleDate;

    @ManyToOne
    @JsonIgnoreProperties("sales")
    private User user;

    @OneToMany(mappedBy = "sale")
    private Set<ProductSale> productSales = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public Sale total(Long total) {
        this.total = total;
        return this;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Instant getSaleDate() {
        return saleDate;
    }

    public Sale saleDate(Instant saleDate) {
        this.saleDate = saleDate;
        return this;
    }

    public void setSaleDate(Instant saleDate) {
        this.saleDate = saleDate;
    }

    public Set<ProductSale> getProductSales() {
        return productSales;
    }

    public Sale productSales(Set<ProductSale> productSales) {
        this.productSales = productSales;
        return this;
    }

    public Sale addProductSale(ProductSale productSale) {
        this.productSales.add(productSale);
        productSale.setSale(this);
        return this;
    }

    public Sale removeProductSale(ProductSale productSale) {
        this.productSales.remove(productSale);
        productSale.setSale(null);
        return this;
    }

    public void setProductSales(Set<ProductSale> productSales) {
        this.productSales = productSales;
    }

    public User getUser() {
        return user;
    }

    public Sale user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
