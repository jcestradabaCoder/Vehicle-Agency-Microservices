package com.jc.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jc.user.entity.User;
import com.jc.user.feignclient.CarFeignClient;
import com.jc.user.feignclient.MotorcycleFeignClient;
import com.jc.user.model.Car;
import com.jc.user.model.Motorcycle;
import com.jc.user.repository.UserRepository;
import com.jc.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CarFeignClient carFeignClient;
	
	@Autowired
	private MotorcycleFeignClient motorcycleFeignClient;
	
		
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(null);
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(Integer userId, User userRequest) {
		User user = userRepository.findById(userId).orElseThrow(null);
		
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setPhoneNumber(userRequest.getPhoneNumber());
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(null);
		userRepository.delete(user);
	}
	
	@Override
	public List<Car> getAllCars(Integer userId) {
		List<Car> carLst = restTemplate.getForObject("http://localhost:8002/car/user/" + userId, List.class);
		return carLst;
	}
	
	@Override
	public List<Motorcycle> getAllMotorcycles(Integer userId) {
		List<Motorcycle> motorcycleLst = restTemplate.getForObject("http://localhost:8003/motorcycle/user/" + userId, List.class);
		return motorcycleLst;
	}
	
	@Override
	public Car createCar(Integer userId, Car car) {
		car.setUserId(userId);
		Car newCar = carFeignClient.createCar(car);
		return newCar;
	}
	
	@Override
	public Motorcycle createMotorcycle(Integer userId, Motorcycle motorcycle) {
		motorcycle.setUserId(userId);
		Motorcycle newMotorcycle = motorcycleFeignClient.createMotorcycle(motorcycle);
		return newMotorcycle;
	}
	
	@Override
	public Map<String, Object> getUserAndVehicles(Integer userId) {
		Map<String, Object> res = new HashMap<>();
		
		User user = userRepository.findById(userId).orElseThrow(null);
		
		if(user == null) {
			res.put("Message", "User not found!");
		}
		res.put("User", user);
		
		List<Car> carLst = carFeignClient.getCars(userId);
		
		if(carLst.isEmpty()) {
			res.put("Message", "user does not have cars!");
		}
		else {
			res.put("Cars", carLst);
		}
		
		List<Motorcycle> motorcycleLst = motorcycleFeignClient.getMotorcycles(userId);
		
		if(motorcycleLst.isEmpty()) {
			res.put("Message", "user does not have motorcycles!");
		}
		else {
			res.put("Motorcycles", motorcycleLst);
		}
		
		return res;
	}
}