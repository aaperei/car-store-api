package com.alison.carstore.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

/**
 * {@link Car} represents the Car Entity 
 * 
 * @author Alison Augusto Miranda Pereira
 *
 */

@Data
@Entity
@Table(name = "car")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "model", length = 50)
	private String model;

	@Column(name = "brand", length = 50)
	@Enumerated(EnumType.STRING)
	private Brand brand;

	@Column(name = "year")
	private Integer year;

	@Column(name = "description", length = 250)
	private String description;

	@Column(name = "sold")
	private Boolean sold;

	@Column(name = "created")
	private Date created;

	@Column(name = "updated")
	private Date updated;
	
	/**
	 * Updates this {@link Car} object through another {@link Car} object parameter
	 * 
	 * @param car the {@link Car} modified object
	 */
	public void update(final Car car) {
		this.setModel(car.getModel());
		this.setBrand(car.getBrand());
		this.setYear(car.getYear());
		this.setDescription(car.getDescription());
		this.setSold(car.getSold());
	}
	
	/**
	 * Updates this {@link Car} object through another {@link Car} object parameter
	 * 
	 * @param car the {@link Car} modified object
	 */
	public void updateNotNullAttributes(final Car car) {
		this.setModel(car.getModel() != null ? car.getModel() : this.getModel());
		this.setBrand(car.getBrand() != null ? car.getBrand() : this.getBrand());
		this.setYear(car.getYear() != null ? car.getYear() : this.getYear());
		this.setDescription(car.getDescription() != null ? car.getDescription() : this.getDescription());
		this.setSold(car.getSold() != null ? car.getSold() : this.getSold());
	}

	@PrePersist
	private void onCreation() {
		this.setCreated(new Date());
		this.setUpdated(new Date());
	}

	@PreUpdate
	private void onUpdate() {
		this.setUpdated(new Date());
	}

}
