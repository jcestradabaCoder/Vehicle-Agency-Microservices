package com.jc.user.controller;

import java.util.List;
import java.util.Map;

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

import com.jc.user.entity.User;
import com.jc.user.model.Car;
import com.jc.user.model.Motorcycle;
import com.jc.user.service.UserService;

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
	
	@GetMapping("/car/{userId}")
	public ResponseEntity<List<Car>> listCars(@PathVariable(name = "userId") Integer userId) { 
		User user = userService.getUserById(userId);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Car> carLst = userService.getAllCars(userId);
		return ResponseEntity.ok(carLst);
	}
	
	@GetMapping("/motorcycle/{userId}")
	public ResponseEntity<List<Motorcycle>> listMotorcycles(@PathVariable(name = "userId") Integer userId) { 
		User user = userService.getUserById(userId);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Motorcycle> motorcycleLst = userService.getAllMotorcycles(userId);
		return ResponseEntity.ok(motorcycleLst);
	}
	
	@PostMapping("/car/{userId}")
	public ResponseEntity<Car> createCar(@PathVariable(name = "userId") Integer userId, @RequestBody Car car) {
		Car newCar = userService.createCar(userId, car);
		return ResponseEntity.ok(newCar);
	}
	
	@PostMapping("/motorcycle/{userId}")
	public ResponseEntity<Motorcycle> createMotorcycle(@PathVariable(name = "userId") Integer userId, @RequestBody Motorcycle motorcycle) {
		Motorcycle newMotorcycle = userService.createMotorcycle(userId, motorcycle);
		return ResponseEntity.ok(newMotorcycle);
	}
	
	@GetMapping("/all/{userId}")
	public ResponseEntity<Map<String, Object>> getVehicles(@PathVariable(name = "userId") Integer userId) {
		
		Map<String, Object> res = userService.getUserAndVehicles(userId);
		return ResponseEntity.ok(res);
	}
}