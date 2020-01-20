package com.todo1.store.infrastructure.persistence.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@NotNull
	@Column(name = "description", nullable = false)
	private String description;

	@NotNull
	@Column(name = "price", nullable = false)
	private Long price;

	@Lob
	@Column(name = "image")
	private byte[] image;

	@Column(name = "image_content_type")
	private String imageContentType;

	@Column(name = "date_update")
	private Instant dateUpdate;

	@OneToMany(mappedBy = "product")
	private Set<ProductSale> productSales = new HashSet<>();
	@OneToMany(mappedBy = "product")
	private Set<ProductPurchase> productPurchases = new HashSet<>();
	@OneToMany(mappedBy = "product")
	private Set<Stock> descriptions = new HashSet<>();
	@ManyToOne
	@JsonIgnoreProperties("products")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public Product description(String description) {
		this.description = description;
		return this;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public Product price(Long price) {
		this.price = price;
		return this;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public Product image(byte[] image) {
		this.image = image;
		return this;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public Product imageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
		return this;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public Instant getDateUpdate() {
		return dateUpdate;
	}

	public Product dateUpdate(Instant dateUpdate) {
		this.dateUpdate = dateUpdate;
		return this;
	}

	public void setDateUpdate(Instant dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public Set<ProductSale> getProductSales() {
		return productSales;
	}

	public Product productSales(Set<ProductSale> productSales) {
		this.productSales = productSales;
		return this;
	}

	public Product addProductSale(ProductSale productSale) {
		this.productSales.add(productSale);
		productSale.setProduct(this);
		return this;
	}

	public Product removeProductSale(ProductSale productSale) {
		this.productSales.remove(productSale);
		productSale.setProduct(null);
		return this;
	}

	public void setProductSales(Set<ProductSale> productSales) {
		this.productSales = productSales;
	}

	public Set<ProductPurchase> getProductPurchases() {
		return productPurchases;
	}

	public Product productPurchases(Set<ProductPurchase> productPurchases) {
		this.productPurchases = productPurchases;
		return this;
	}

	public Product addProductPurchase(ProductPurchase productPurchase) {
		this.productPurchases.add(productPurchase);
		productPurchase.setProduct(this);
		return this;
	}

	public Product removeProductPurchase(ProductPurchase productPurchase) {
		this.productPurchases.remove(productPurchase);
		productPurchase.setProduct(null);
		return this;
	}

	public void setProductPurchases(Set<ProductPurchase> productPurchases) {
		this.productPurchases = productPurchases;
	}

	public Set<Stock> getDescriptions() {
		return descriptions;
	}

	public Product descriptions(Set<Stock> stocks) {
		this.descriptions = stocks;
		return this;
	}

	public Product addDescription(Stock stock) {
		this.descriptions.add(stock);
		stock.setProduct(this);
		return this;
	}

	public Product removeDescription(Stock stock) {
		this.descriptions.remove(stock);
		stock.setProduct(null);
		return this;
	}

	public void setDescriptions(Set<Stock> stocks) {
		this.descriptions = stocks;
	}

	public User getUser() {
		return user;
	}

	public Product user(User user) {
		this.user = user;
		return this;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
