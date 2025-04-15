package com.example.carte_fedelta.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@NotBlank(message = "Il negozio deve avere un nome")
	private String store_name;
	
	@Column(length = 100000000)
	//@NotBlank(message = "Il negozio deve avere un logo")
	private byte[] logo;
	
	@NotBlank(message = "Il logo deve avere un nome")
	private String logo_name;
	
	public Store(Long id, String store_name, String logo_name) {
		super();
		this.id = id;
		this.store_name = store_name;
		this.logo_name = logo_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return store_name;
	}

	public void setName(String name) {
		this.store_name = name;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getLogo_name() {
		return logo_name;
	}

	public void setLogo_name(String logo_name) {
		this.logo_name = logo_name;
	}
	
	
	
/*
 * CREATE TABLE `carte_fedelta`.`store` (
  `store_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `logo` LONGBLOB NOT NULL,
  `logo_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`store_id`));

 */
}
