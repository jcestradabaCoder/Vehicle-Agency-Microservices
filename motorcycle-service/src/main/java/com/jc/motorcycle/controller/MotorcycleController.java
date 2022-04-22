package com.jc.motorcycle.controller;

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

import com.jc.motorcycle.entity.Motorcycle;
import com.jc.motorcycle.service.MotorcycleService;

@RestController
@RequestMapping("/motorcycle")
public class MotorcycleController {
	
	@Autowired
	private MotorcycleService motorcycleService;
	
	
	@PostMapping
	public ResponseEntity<Motorcycle> createMotorcycle(@Valid @RequestBody Motorcycle motorcycle) {
		Motorcycle newMotorcycle = motorcycleService.createMotorcycle(motorcycle);
		return ResponseEntity.ok(newMotorcycle);
	}
	
	@GetMapping
	public ResponseEntity<List<Motorcycle>> getAllMotorcycles() {
		List<Motorcycle> motorcycleLst = motorcycleService.getAllMotorcycles();
		
		if(motorcycleLst.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motorcycleLst);
	}
	
	@GetMapping("/{motorcycleId}")
	public ResponseEntity<Motorcycle> getMotorcycleById(@PathVariable(name = "motorcycleId") Integer motorcycleId) {
		Motorcycle motorcycle = motorcycleService.getMotorcycleById(motorcycleId);
		
		if(motorcycle == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(motorcycle);
	}
	
	@PutMapping("/{motorcycleId}")
	public ResponseEntity<Motorcycle> updateMotorcycle(@PathVariable(name = "motorcycleId") Integer motorcycleId, @Valid @RequestBody Motorcycle motorcycleRequest) {
		Motorcycle motorcycleResponse = motorcycleService.updateMotorcycle(motorcycleId, motorcycleRequest);
		return ResponseEntity.ok(motorcycleResponse);
	}
	
	@DeleteMapping("/{motorcycleId}")
	public ResponseEntity<String> deleteMotorcycle(@PathVariable(name = "motorcycleId") Integer motorcycleId) {
		motorcycleService.deleteMotorcycle(motorcycleId);
		return ResponseEntity.ok("Motorcycle deleted successfully!");
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Motorcycle>> getAllMotorcyclesByUser(@PathVariable(name = "userId") Integer userId) {
		List<Motorcycle> motorcycleLst = motorcycleService.getAllMotorcyclesByUserId(userId);
		
		if(motorcycleLst.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motorcycleLst);
	}
}