package com.example.carte_fedelta.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long number;
	
	@ManyToOne
	@JoinColumn(name = "store_name")
	private Store store_name;
	
	//@Column(length = 100)
	//private String notes;
	
	public Card() {}
	
	public Card(Long id, Long number) {
		super();
		this.id = id;
		this.number = number;
	}
	
	public Card(Long id, Long number, Store store) {
		super();
		this.id = id;
		this.number = number;
		this.store_name=store;
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

	public Store getStore_name() {
		return store_name;
	}

	public void setStore_name(Store store_name) {
		this.store_name = store_name;
	}
/*
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	*/
	

/*
 * CREATE TABLE `carte_fedelta`.`card` (
  `card_id` BIGINT NOT NULL AUTO_INCREMENT,
  `number` BIGINT NOT NULL,
  `store_name` VARCHAR(45) NOT NULL,
  `notes` TEXT NULL,
  PRIMARY KEY (`card_id`));

 */
}
