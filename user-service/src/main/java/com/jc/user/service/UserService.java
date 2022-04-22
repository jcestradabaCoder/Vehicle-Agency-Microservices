package com.jc.user.service;

import java.util.List;
import java.util.Map;

import com.jc.user.entity.User;
import com.jc.user.model.Car;
import com.jc.user.model.Motorcycle;

public interface UserService {
	
	List<Car> getAllCars(Integer userId);
	List<Motorcycle> getAllMotorcycles(Integer userId);
	Car createCar(Integer userId, Car car);
	Motorcycle createMotorcycle(Integer userId, Motorcycle motorcycle);
	Map<String, Object> getUserAndVehicles(Integer userId);
	
	List<User> getAllUsers();
	User getUserById(Integer userId);
	User createUser(User user);
	User updateUser(Integer userId, User userRequest);
	void deleteUser(Integer userId);
}