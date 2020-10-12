package com.alison.carstore.domain;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.alison.carstore.rest.CarController;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;

/**
 * {@link CarFacade} implements the facade design pattern in order to hide implementation details from the {@link CarController}  
 * 
 * @author Alison Augusto Miranda Pereira
 *
 */

@Component
@AllArgsConstructor
public class CarFacade {
	
	private final CarService carService;
	
	/**
	 * Saves a given {@link Car} object into database
	 * 
	 * @param carJson the {@link Car} object in JSON format
	 * @throws JsonProcessingException if JSON object could not be parsed into an {@link Car} object 
	 */
	@Transactional
	public Car addCar(final String carJson) throws JsonProcessingException{
		final Car car = carService.serializeCar(carJson);
		return carService.saveCar(car);
	}
	
	/**
	 * Updates all {@link Car} attributes
	 * 
	 * @param id the identifier to {@link Car} instance
	 * @param carJson is a JSON object of {@link Car} with new values
	 * 
	 * @throws JsonProcessingException if JSON object could not be parsed into an {@link Car} object 
	 */
	@Transactional
	public Car updateCar(final Long id, final String carJson) throws JsonProcessingException {
		final Car persistedCar = this.findById(id);
		final Car modifiedCar = carService.serializeCar(carJson);
		
		if(modifiedCar.getId().equals(persistedCar.getId())) {
			persistedCar.update(modifiedCar);
			return carService.saveCar(persistedCar);
		} else {
			throw new IllegalArgumentException("Specified id does not matches with JSON object id");
		}
	}
	
	/**
	 * Updates some {@link Car} attributes
	 * 
	 * @param id the identifier to {@link Car} instance
	 * @param carJson is a JSON object of {@link Car} with new values
	 * 
	 * @throws JsonProcessingException if JSON object could not be parsed into an {@link Car} object 
	 */
	@Transactional
	public Car updateSpecificCarAttributes(final Long id, final String carJson) throws JsonProcessingException {
		final Car persistedCar = this.findById(id);
		final Car modifiedCar = carService.serializeCar(carJson);
		if(modifiedCar.getId().equals(persistedCar.getId())) {
			persistedCar.updateNotNullAttributes(modifiedCar);
			return carService.saveCar(persistedCar);
		} else {
			throw new IllegalArgumentException("Specified id does not matches with JSON object id");
		}
	}

	/**
	 * Finds a list of all persisted Cars
	 * 
	 * @return a {@link List} of {@link Car}
	 */
	public List<Car> findAll() {
		return carService.findAll();
		
	}

	/**
	 * Search an {@link Car} by its id
	 * 
	 * @param id of the {@link Car} desired
	 * @return if founded, a fulfilled {@link Car} object, otherwise a new one
	 */
	public Car findById(final Long id) {
		return carService.findById(id);
	}
	
	/**
	 * Gets a list of cars from database according to the parameter querySearch
	 * 
	 * querySearch parameter to be searched
	 * @return a {@link List} of {@link Car}
	 */
	public List<Car> findByParameter(final String querySearch) {
		return carService.findByParameter(querySearch);
	}

	/**
	 * Deletes an {@link Car} object by its id
	 * 
	 * @param id parameter used to delete an {@link Car} object in database
	 */
	@Transactional
	public boolean deleteCar(final Long id) {
		carService.deleteCar(id);
		return true;
	}
	
	/**
	 * Serializes a {@link Car} object into a JSON {@link String} 
	 * 
	 * @param car the {@link Car} object to be serialized
	 * @return a {@link String} object that represents the JSON
	 * @throws JsonProcessingException if the JSON parameter could not be mapped into a {@link Car} object
	 */
	public String serializeCarToString(final Car car) throws JsonProcessingException {
		return carService.serializeCarToString(car);
	}

}
