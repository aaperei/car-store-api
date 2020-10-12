package com.alison.carstore.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Repository} is the repository layer responsible for handling database operations for {@link Car} objects 
 * 
 * @author Alison Augusto Miranda Pereira
 *
 */

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
	
	public List<Car> findByModelContaining(final String model);
	public List<Car> findByBrandContaining(final String brand);
	public List<Car> findByYearContaining(final String year);
	public List<Car> findByDescriptionContaining(final String description);
	
}
