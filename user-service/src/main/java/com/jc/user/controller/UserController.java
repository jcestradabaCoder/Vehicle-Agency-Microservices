package com.jc.user.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jc.user.entity.User;
import com.jc.user.model.Car;
import com.jc.user.model.Motorcycle;
import com.jc.user.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User newUser = userService.createUser(user);
		return ResponseEntity.ok(newUser);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userLst = userService.getAllUsers();
		
		if(userLst.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(userLst);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable(name = "userId") Integer userId) {
		User user = userService.getUserById(userId);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable(name = "userId") Integer userId, @Valid @RequestBody User userRequest) {
		User userResponse = userService.updateUser(userId, userRequest);
		return ResponseEntity.ok(userResponse);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable(name = "userId") Integer userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok("User deleted successfully!");
		
	}
	
	@CircuitBreaker(name = "carCB", fallbackMethod = "fallBackGetCars")
	@GetMapping("/car/{userId}")
	public ResponseEntity<List<Car>> listCars(@PathVariable(name = "userId") Integer userId) { 
		User user = userService.getUserById(userId);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Car> carLst = userService.getAllCars(userId);
		return ResponseEntity.ok(carLst);
	}
	
	@CircuitBreaker(name = "motorcicleCB", fallbackMethod = "fallBackGetMotorcycles")
	@GetMapping("/motorcycle/{userId}")
	public ResponseEntity<List<Motorcycle>> listMotorcycles(@PathVariable(name = "userId") Integer userId) { 
		User user = userService.getUserById(userId);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Motorcycle> motorcycleLst = userService.getAllMotorcycles(userId);
		return ResponseEntity.ok(motorcycleLst);
	}
	
	@CircuitBreaker(name = "carCB", fallbackMethod = "fallBackSaveCar")
	@PostMapping("/car/{userId}")
	public ResponseEntity<Car> createCar(@PathVariable(name = "userId") Integer userId, @RequestBody Car car) {
		Car newCar = userService.createCar(userId, car);
		return ResponseEntity.ok(newCar);
	}
	
	@CircuitBreaker(name = "motorcicleCB", fallbackMethod = "fallBackSaveMotorcycle")
	@PostMapping("/motorcycle/{userId}")
	public ResponseEntity<Motorcycle> createMotorcycle(@PathVariable(name = "userId") Integer userId, @RequestBody Motorcycle motorcycle) {
		Motorcycle newMotorcycle = userService.createMotorcycle(userId, motorcycle);
		return ResponseEntity.ok(newMotorcycle);
	}
	
	@CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetAll")
	@GetMapping("/all/{userId}")
	public ResponseEntity<Map<String, Object>> getVehicles(@PathVariable(name = "userId") Integer userId) {
		
		Map<String, Object> res = userService.getUserAndVehicles(userId);
		return ResponseEntity.ok(res);
	}
	
	private ResponseEntity<List<Car>> fallBackGetCars(@PathVariable(name = "userId") Integer userId, RuntimeException exception) {
		return new ResponseEntity("user's cars with id: " + userId + " are unavailable.", HttpStatus.OK);
	}
	
	private ResponseEntity<List<Motorcycle>> fallBackGetMotorcycles(@PathVariable(name = "userId") Integer userId, RuntimeException exception) {
		return new ResponseEntity("user's motorcycles with id: " + userId + " are unavailable.", HttpStatus.OK);
	}
	
	public ResponseEntity<Car> fallBackSaveCar(@PathVariable(name = "userId") Integer userId, @RequestBody Car car, RuntimeException exception) {
		return new ResponseEntity("user with id: " + userId + " are not able to buy cars.", HttpStatus.OK);
	}
	
	public ResponseEntity<Motorcycle> fallBackSaveMotorcycle(@PathVariable(name = "userId") Integer userId, @RequestBody Motorcycle motorcycle, RuntimeException exception) {
		return new ResponseEntity("user with id: " + userId + " are not able to buy motorcycles.", HttpStatus.OK);
	}
	
	public ResponseEntity<Map<String, Object>> fallBackGetAll(@PathVariable(name = "userId") Integer userId, RuntimeException exception) {
		return new ResponseEntity("user's vehicles with id: " + userId + " are unavailable.", HttpStatus.OK);
	}
}