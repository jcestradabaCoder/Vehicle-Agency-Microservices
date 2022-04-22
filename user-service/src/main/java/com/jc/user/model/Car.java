package com.jc.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Car {
	
	private String brand;
	private String model;
	private Integer userId;
}