package com.jc.motorcycle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jc.motorcycle.entity.Motorcycle;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Integer>{
	
	List<Motorcycle> findMotorcyclesByUserId(Integer userId);
}