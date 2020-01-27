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
@Table(name = "product_sale")
public class ProductSale implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@NotNull
	@Column(name = "amount", nullable = false)
	private Long amount;

	@ManyToOne
	@JsonIgnoreProperties("productSales")
	private Product product;

	@ManyToOne
	@JsonIgnoreProperties("productSales")
	private Sale sale;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount() {
		return amount;
	}

	public ProductSale amount(Long amount) {
		this.amount = amount;
		return this;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public ProductSale product(Product product) {
		this.product = product;
		return this;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Sale getSale() {
		return sale;
	}

	public ProductSale sale(Sale sale) {
		this.sale = sale;
		return this;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

}
