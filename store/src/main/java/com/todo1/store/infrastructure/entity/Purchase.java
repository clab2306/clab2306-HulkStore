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
@Table(name = "purchase")
public class Purchase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@NotNull
	@Column(name = "total", nullable = false)
	private Long total;

	@NotNull
	@Column(name = "purchase_date", nullable = false)
	private Instant purchaseDate;

	@OneToMany(mappedBy = "purchase")
	private Set<ProductPurchase> productPurchases = new HashSet<>();
	@ManyToOne
	@JsonIgnoreProperties("purchases")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTotal() {
		return total;
	}

	public Purchase total(Long total) {
		this.total = total;
		return this;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Instant getPurchaseDate() {
		return purchaseDate;
	}

	public Purchase purchaseDate(Instant purchaseDate) {
		this.purchaseDate = purchaseDate;
		return this;
	}

	public void setPurchaseDate(Instant purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Set<ProductPurchase> getProductPurchases() {
		return productPurchases;
	}

	public Purchase productPurchases(Set<ProductPurchase> productPurchases) {
		this.productPurchases = productPurchases;
		return this;
	}

	public Purchase addProductPurchase(ProductPurchase productPurchase) {
		this.productPurchases.add(productPurchase);
		productPurchase.setPurchase(this);
		return this;
	}

	public Purchase removeProductPurchase(ProductPurchase productPurchase) {
		this.productPurchases.remove(productPurchase);
		productPurchase.setPurchase(null);
		return this;
	}

	public void setProductPurchases(Set<ProductPurchase> productPurchases) {
		this.productPurchases = productPurchases;
	}

	public User getUser() {
		return user;
	}

	public Purchase user(User user) {
		this.user = user;
		return this;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
