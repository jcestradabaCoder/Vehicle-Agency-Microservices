package com.jc.car.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "tb_car")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private Integer id;
	
	@NotNull
	@Column(name = "brand", nullable = false)
	private String brand;
	
	@NotNull
	@Column(name = "model", nullable = false)
	private String model;
	
	@Column(name = "userId", nullable = false)
	private Integer userId;
}