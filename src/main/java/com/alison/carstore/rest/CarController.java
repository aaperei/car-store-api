package com.alison.carstore.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.jboss.logging.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alison.carstore.domain.Car;
import com.alison.carstore.domain.CarFacade;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;

/**
 * {@link CarController} is the controller layer responsible for handling HTTP
 * requests for all Car Store APIs
 * 
 * @author Alison Augusto Miranda Pereira
 *
 */

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class CarController {

	private CarFacade carFacade;

	private static final Logger logger = Logger.getLogger(CarController.class);

	/**
	 * Gets all cars in database
	 * 
	 * @return a {@link List} of all {@link Car} in database
	 */
	@GetMapping
	public ResponseEntity<List<Car>> getAllCars() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(carFacade.findAll());
		} catch (Exception e) {
			logger.error("Error getting all cars.", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/**
	 * Gets a list of cars from database according to the parameter querySearch
	 * 
	 * querySearch parameter to be searched
	 * 
	 * @return a {@link List} of {@link Car}
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Car>> findByParameter(
			@RequestParam(value = "q", required = true, defaultValue = "") final String querySearch) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(carFacade.findByParameter(querySearch));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	/**
	 * Method to find an {@link Car} by its id
	 * 
	 * @param id used to search
	 * @return an {@link Car} object
	 */
	@GetMapping("{id}")
	public ResponseEntity<Car> findCarById(@PathVariable final Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(carFacade.findById(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

	}
	
	@PostMapping
	public ResponseEntity<Car> addCar(@RequestBody final String carJson) {
		try {

			carFacade.addCar(carJson);
			return ResponseEntity.status(HttpStatus.OK).build();

		} catch (JsonProcessingException e) {
			logger.error("Error creating a new car.", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (DataIntegrityViolationException e) {
			logger.error("Erro while creating Car object. Constraints violated.", e);
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}

	}

	/**
	 * Updates all {@link Car} object attributes through a JSON object
	 * 
	 * @param id      identifier to a {@link Car} object
	 * @param carJson JSON object of {@link Car} with new values
	 * @return a {@link ResponseEntity} with request status
	 */
	@PutMapping("{id}")
	public ResponseEntity<?> updateAllCarAttributes(@PathVariable final Long id, @RequestBody final String carJson) {
		try {

			carFacade.updateCar(id, carJson);
			return ResponseEntity.status(HttpStatus.OK).build();

		} catch (JsonProcessingException | IllegalArgumentException e) {
			logger.error("Erro while reading JSON parameter.", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (EntityNotFoundException e) {
			logger.error("Erro while updating Car object. Entity not found.", e);
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		} catch (DataIntegrityViolationException e) {
			logger.error("Erro while updating Car object. Constraints violated.", e);
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}

	}

	/**
	 * Updates some {@link Car} object attributes through a JSON object
	 * 
	 * @param id      identifier to a {@link Car} object
	 * @param carJson JSON object of {@link Car} with new values
	 * @return a {@link ResponseEntity} with request status
	 */
	@PatchMapping("{id}")
	public ResponseEntity<?> updateSpecificCarAttributes(@PathVariable final Long id,
			@RequestBody final String carJson) {
		try {

			carFacade.updateSpecificCarAttributes(id, carJson);
			return ResponseEntity.status(HttpStatus.OK).build();

		} catch (JsonProcessingException | IllegalArgumentException e) {
			logger.error("Erro while reading JSON parameter.", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (EntityNotFoundException e) {
			logger.error("Erro while updating Car object. Entity not found.", e);
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		} catch (DataIntegrityViolationException e) {
			logger.error("Erro while updating Car object. Constraints violated.", e);
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
	}

	/**
	 * Method to delete a {@link Car} object by its id
	 * 
	 * @param id given to delete an {@link Car} object
	 * @return a {@link ResponseEntity} with request status
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteCar(@PathVariable final Long id) {
		carFacade.deleteCar(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
