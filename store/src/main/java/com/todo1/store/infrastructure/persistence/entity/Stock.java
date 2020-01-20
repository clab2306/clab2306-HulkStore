package com.todo1.store.infrastructure.persistence.entity;

import java.io.Serializable;
import java.time.Instant;

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
 * A Stock.
 */
@Entity
@Table(name = "stock")
public class Stock implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@NotNull
	@Column(name = "amount", nullable = false)
	private Long amount;

	@NotNull
	@Column(name = "update_date", nullable = false)
	private Instant updateDate;

	@ManyToOne
	@JsonIgnoreProperties("descriptions")
	private Product product;

	@ManyToOne
	@JsonIgnoreProperties("stocks")
	private User updateUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount() {
		return amount;
	}

	public Stock amount(Long amount) {
		this.amount = amount;
		return this;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Instant getUpdateDate() {
		return updateDate;
	}

	public Stock updateDate(Instant updateDate) {
		this.updateDate = updateDate;
		return this;
	}

	public void setUpdateDate(Instant updateDate) {
		this.updateDate = updateDate;
	}

	public Product getProduct() {
		return product;
	}

	public Stock product(Product product) {
		this.product = product;
		return this;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public Stock updateUser(User user) {
		this.updateUser = user;
		return this;
	}

	public void setUpdateUser(User user) {
		this.updateUser = user;
	}

}
