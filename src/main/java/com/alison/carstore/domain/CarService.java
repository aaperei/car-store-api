package com.alison.carstore.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

/**
 * {@link CarService} is the service layer responsible for handling car operations 
 * 
 * @author Alison Augusto Miranda Pereira
 *
 */

@AllArgsConstructor
@Service
public class CarService {
	
	private CarRepository carRepository;
	private static final ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Serializes a JSON into an {@link Car} object
	 * 
	 * @param carJson is a JSON object of {@link Car}
	 * @return an {@link Car} instance
	 * @throws JsonProcessingException if the JSON parameter could not be mapped into a {@link Car} object
	 */
	public Car serializeCar(final String carJson) throws JsonProcessingException {
		return mapper.readValue(carJson, Car.class);
	}
	
	/**
	 * Serializes a {@link Car} object into a JSON {@link String} 
	 * 
	 * @param car the {@link Car} object to be serialized
	 * @return a {@link String} object that represents the JSON
	 * @throws JsonProcessingException if the JSON parameter could not be mapped into a {@link Car} object
	 */
	public String serializeCarToString(final Car car) throws JsonProcessingException {
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(car);
	}
	
	/**
	 * Saves a given {@link Car} object into database 
	 * 
	 * @param car the {@link Car} object to be persisted
	 * @return the persisted {@link Car}
	 */
	public Car saveCar(Car car) {
		return carRepository.save(car);
		
	}
	
	/**
	 * Gets all persisted cars from database
	 * 
	 * @return a {@link List} of {@link Car}
	 * * @throws {@link EntityNotFoundException} if no Entity was found on database
	 */
	public List<Car> findAll() {
		return carRepository.findAll();
	}
	
	/**
	 * Gets a list of cars from database according to the parameter querySearch
	 * 
	 * querySearch parameter to be searched
	 * @return a {@link List} of {@link Car}
	 */
	public List<Car> findByParameter(String querySearch) {
		List<Car> cars = new ArrayList<>();
		
		if(querySearch.isEmpty()) {
			throw new EntityNotFoundException();
		}
				
		cars.addAll(carRepository.findByModelContaining(querySearch));
		cars.addAll(carRepository.findByDescriptionContaining(querySearch));
		
		if(cars.isEmpty()) {
			throw new EntityNotFoundException();
		}
		
		return cars;
	}

	/**
	 * Looks up for a {@link Car} by its id
	 * 
	 * @param id of {@link Car} searched
	 * @return the searched {@link Car}
	 * @throws {@link EntityNotFoundException} if id was not found on database
	 */
	public Car findById(final Long id) {
		return carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id = '" + id.toString() + "' was not found"));
	}
	
	/**
	 * Deletes a {@link Car} object by its id
	 * 
	 * @param id parameter used to delete a {@link Car} object in database
	 */
	public void deleteCar(final Long id) {
		carRepository.deleteById(id);
	}

}
