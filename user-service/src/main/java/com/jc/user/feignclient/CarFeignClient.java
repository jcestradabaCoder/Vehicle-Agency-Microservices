package com.jc.user.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jc.user.model.Car;

@FeignClient(name = "car-service")
@RequestMapping("/car")
public interface CarFeignClient {
	
	@PostMapping
	public Car createCar(@RequestBody Car car);
	
	@GetMapping("/user/{userId}")
	public List<Car> getCars(@PathVariable(name = "userId") Integer userId);
}