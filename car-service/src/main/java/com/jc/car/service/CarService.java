package com.jc.car.service;

import java.util.List;

import com.jc.car.entity.Car;

public interface CarService {

	List<Car> getAllCars();
	Car getCarById(Integer carId);
	List<Car> getAllCarsByUserId(Integer userId);
	Car createCar(Car car);
	Car updateCar(Integer carId, Car carRequest);
	void deleteCar(Integer carId);
}