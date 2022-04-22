package com.jc.car.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jc.car.entity.Car;
import com.jc.car.repository.CarRepository;
import com.jc.car.service.CarService;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	
	@Override
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@Override
	public Car getCarById(Integer carId) {
		return carRepository.findById(carId).orElseThrow(null);
	}
	
	@Override
	public List<Car> getAllCarsByUserId(Integer userId) {
		return carRepository.findCarsByUserId(userId);
	}

	@Override
	public Car createCar(Car car) {
		return carRepository.save(car);
	}

	@Override
	public Car updateCar(Integer carId, Car carRequest) {
		Car car = carRepository.findById(carId).orElseThrow(null);
		
		car.setBrand(carRequest.getBrand());
		car.setModel(carRequest.getModel());
		car.setUserId(carRequest.getUserId());
		return carRepository.save(car);
	}

	@Override
	public void deleteCar(Integer carId) {
		Car car = carRepository.findById(carId).orElseThrow(null);
		carRepository.delete(car);
	}
} 