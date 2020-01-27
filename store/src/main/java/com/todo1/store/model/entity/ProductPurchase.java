package com.todo1.store.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "product_purchase")
public class ProductPurchase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@NotNull
	@Column(name = "amount", nullable = false)
	private Long amount;

	@ManyToOne
	@JsonIgnoreProperties("productPurchases")
	private Product product;

	@ManyToOne
	@JsonIgnoreProperties("productPurchases")
	private Purchase purchase;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount() {
		return amount;
	}

	public ProductPurchase amount(Long amount) {
		this.amount = amount;
		return this;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public ProductPurchase product(Product product) {
		this.product = product;
		return this;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public ProductPurchase purchase(Purchase purchase) {
		this.purchase = purchase;
		return this;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

}
