package com.jc.motorcycle.service;

import java.util.List;

import com.jc.motorcycle.entity.Motorcycle;

public interface MotorcycleService {

	List<Motorcycle> getAllMotorcycles();
	Motorcycle getMotorcycleById(Integer motorcycleId);
	List<Motorcycle> getAllMotorcyclesByUserId(Integer userId);
	Motorcycle createMotorcycle(Motorcycle motorcycle);
	Motorcycle updateMotorcycle(Integer MotorcycleId, Motorcycle MotorcycleRequest);
	void deleteMotorcycle(Integer MotorcycleId);
}