package com.alison.carstore.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
public class CarFacadeIntegrationTests {
	
	@Autowired
	private CarFacade carFacade;
	
	private String 	newCarJson =  "{\"model\": \"Punto Super Power\", "
							     + "\"brand\": \"FIAT\","
							     + "\"year\": 2029, "
							     + "\"description\": \"Cor Branca, completo\","
							     + "\"sold\": false }";
	
	private String newCarJsonError =  "{\"model\": \"Punto Super Power\", "
			                         + "\"brand\": \"FIAT-50\","
			                         + "\"year\": 2029, "
			                         + "\"description\": \"Cor Branca, completo\","
			                         + "\"sold\": false }";
	
	private String invalidBrand = "FIAT-ERROR";
	
	@Test
	public void addCarTest() throws JsonProcessingException {
		final Car car = carFacade.addCar(newCarJson);
		assertTrue(car.getId() != null && car.getCreated() != null && car.getUpdated() != null);
	}
	
	@Test
	public void addCarTestErrorProcessingJson() throws JsonProcessingException {
		assertThrows(JsonProcessingException.class, () ->{
			carFacade.addCar(newCarJsonError);
		});
	}
	
	@Test
	public void updateCarTest() throws JsonProcessingException {
		final Date now = new Date();
		final String newDescription = "New Description";
		Car car = carFacade.addCar(newCarJson);
		car.setDescription(newDescription);
		final String carJson = carFacade.serializeCarToString(car);
		car = carFacade.updateCar(car.getId(), carJson);
		assertEquals(true, car.getUpdated().after(now) && newDescription.equals(car.getDescription()));
	}
	
	@Test
	public void updateCarTestErrorProcessingJson() throws JsonProcessingException {
		Car car = carFacade.addCar(newCarJson);
		
		final Brand  currentBrand = car.getBrand();
		final String carJson = carFacade.serializeCarToString(car).replace(currentBrand.toString(), invalidBrand);;
		
		assertThrows(JsonProcessingException.class, () ->{
			carFacade.updateCar(car.getId(), carJson);
		});
	}
	
	@Test
	public void updateCarTestInvalidId()throws JsonProcessingException {
		Car car = carFacade.addCar(newCarJson);
		final String carJson = carFacade.serializeCarToString(car);
		
		Car auxCar = carFacade.addCar(newCarJson);
		
		assertThrows(IllegalArgumentException.class, () ->{
			carFacade.updateCar(auxCar.getId(), carJson);
		});
		
	}
	
	@Test
	public void updateSpecificCarAttributesTest() throws JsonProcessingException {
		final Date now = new Date();
		final String newDescription = "New Description";
		Car car = carFacade.addCar(newCarJson);
		car.setDescription(newDescription);
		
		final Brand  currentBrand = car.getBrand();
		car.setBrand(null);
		
		String carJson = carFacade.serializeCarToString(car);
		car = carFacade.updateSpecificCarAttributes(car.getId(), carJson);
		assertTrue(car.getUpdated().after(now) && car.getBrand() == currentBrand);
	}
	
	@Test
	public void updateSpecificCarAttributesTestErrorProcessingJson() throws JsonProcessingException {
		Car car = carFacade.addCar(newCarJson);
		
		car.setModel(null);
		final Brand  currentBrand = car.getBrand();		
		final String carJson = carFacade.serializeCarToString(car).replace(currentBrand.toString(), invalidBrand);;
		
		assertThrows(JsonProcessingException.class, () ->{
			carFacade.updateSpecificCarAttributes(car.getId(), carJson);
		});
		
	}
	
	@Test
	public void updateSpecificCarAttributesTestInvalidId()throws JsonProcessingException {
		Car car = carFacade.addCar(newCarJson);
		car.setModel(null);
		final String carJson = carFacade.serializeCarToString(car);
		
		Car auxCar = carFacade.addCar(newCarJson);
		
		assertThrows(IllegalArgumentException.class, () ->{
			carFacade.updateSpecificCarAttributes(auxCar.getId(), carJson);
		});
		
	}
	
	@Test
	public void findByIdTest() throws JsonProcessingException {
		Car car = carFacade.addCar(newCarJson);
		carFacade.findById(car.getId());
	}
	
	@Test
	public void findAllTest() throws JsonProcessingException {
		carFacade.addCar(newCarJson);
		List<Car> cars = carFacade.findAll();
		
		assertTrue(!cars.isEmpty());
	}
	
	@Test
	public void deleteCarTest() throws JsonProcessingException {
		Car car = carFacade.addCar(newCarJson);
		assertTrue(carFacade.deleteCar(car.getId()));
	}
	
	@Test
	public void deleteCarInvalidId() throws JsonProcessingException {
		
		assertThrows(InvalidDataAccessApiUsageException.class, () ->{
			assertTrue(carFacade.deleteCar(null));
		});
	}

}
