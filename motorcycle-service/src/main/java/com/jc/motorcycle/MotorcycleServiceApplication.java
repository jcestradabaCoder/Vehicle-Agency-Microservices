package com.jc.motorcycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MotorcycleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotorcycleServiceApplication.class, args);
	}
}