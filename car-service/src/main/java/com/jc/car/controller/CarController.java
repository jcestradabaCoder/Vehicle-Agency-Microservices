package com.jc.car.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jc.car.entity.Car;
import com.jc.car.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;
	
	
	@PostMapping
	public ResponseEntity<Car> createCar(@Valid @RequestBody Car car) {
		Car newCar = carService.createCar(car);
		return ResponseEntity.ok(newCar);
	}
	
	@GetMapping
	public ResponseEntity<List<Car>> getAllCars() {
		List<Car> carLst = carService.getAllCars();
		
		if(carLst.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carLst);
	}
	
	@GetMapping("/{carId}")
	public ResponseEntity<Car> getCarById(@PathVariable(name = "carId") Integer carId) {
		Car car = carService.getCarById(carId);
		
		if(car == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(car);
	}
	
	@PutMapping("/{carId}")
	public ResponseEntity<Car> updateCar(@PathVariable(name = "carId") Integer carId, @Valid @RequestBody Car carRequest) {
		Car carResponse = carService.updateCar(carId, carRequest);
		return ResponseEntity.ok(carResponse);
	}
	
	@DeleteMapping("/{carId}")
	public ResponseEntity<String> deleteCar(@PathVariable(name = "carId") Integer carId) {
		carService.deleteCar(carId);
		return ResponseEntity.ok("Car deleted successfully!");
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Car>> getAllCarsByUser(@PathVariable(name = "userId") Integer userId) {
		List<Car> carLst = carService.getAllCarsByUserId(userId);
		
		if(carLst.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carLst);
	}
}