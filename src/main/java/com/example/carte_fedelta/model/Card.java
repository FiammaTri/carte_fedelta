package com.example.carte_fedelta.model;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Min(100_000_000_000L)
    @Max(999_999_999_999L)
	private Long number;
	
	@ManyToOne
	@JoinColumn(name = "store_name")
	private Store store;
	
	@Column(length = 100)
	private String notes;
	
	public Card() {}
	
	public Card(Long id, Long number, String notes) {
		super();
		this.id = id;
		this.number = number;
		this.notes = notes;
	}
	
	public Card(Long id, Long number, Store store) {
		super();
		this.id = id;
		this.number = number;
		this.store = store;
	}
	
	public Card(Long id, Long number, Store store, String notes) {
		super();
		this.id = id;
		this.number = number;
		this.notes = notes;
		this.store=store;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	

	

/*
 * CREATE TABLE `carte_fedelta`.`card` (
  `card_id` BIGINT NOT NULL AUTO_INCREMENT,
  `number` BIGINT NOT NULL,
  `store_name` VARCHAR(45) NOT NULL,
  `notes` TEXT NULL,
  PRIMARY KEY (`card_id`));

 */
}
