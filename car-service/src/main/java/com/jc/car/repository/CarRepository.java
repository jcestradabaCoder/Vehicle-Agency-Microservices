package com.jc.car.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jc.car.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
	
	List<Car> findCarsByUserId(Integer userId);
}