package com.jc.motorcycle.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jc.motorcycle.entity.Motorcycle;
import com.jc.motorcycle.repository.MotorcycleRepository;
import com.jc.motorcycle.service.MotorcycleService;

@Service
public class MotorcycleServiceImpl implements MotorcycleService {
	
	@Autowired
	private MotorcycleRepository motorcycleRepository;
	

	@Override
	public List<Motorcycle> getAllMotorcycles() {
		return motorcycleRepository.findAll();
	}

	@Override
	public Motorcycle getMotorcycleById(Integer motorcycleId) {
		return motorcycleRepository.findById(motorcycleId).orElseThrow(null);
	}

	@Override
	public List<Motorcycle> getAllMotorcyclesByUserId(Integer userId) {
		return motorcycleRepository.findMotorcyclesByUserId(userId);
	}

	@Override
	public Motorcycle createMotorcycle(Motorcycle motorcycle) {
		return motorcycleRepository.save(motorcycle);
	}

	@Override
	public Motorcycle updateMotorcycle(Integer MotorcycleId, Motorcycle MotorcycleRequest) {
		Motorcycle motorcycle = motorcycleRepository.findById(MotorcycleId).orElseThrow(null);
		
		motorcycle.setBrand(MotorcycleRequest.getBrand());
		motorcycle.setModel(MotorcycleRequest.getModel());
		motorcycle.setUserId(MotorcycleRequest.getUserId());
		return motorcycleRepository.save(motorcycle);
	}

	@Override
	public void deleteMotorcycle(Integer MotorcycleId) {
		Motorcycle motorcycle = motorcycleRepository.findById(MotorcycleId).orElseThrow(null);
		motorcycleRepository.delete(motorcycle);
	}
}