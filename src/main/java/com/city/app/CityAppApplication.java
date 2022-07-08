package com.city.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityAppApplication.class, args);
	
		String password = "admin";
		String username = "admin";
		
		System.out.println("Password is         : " + password);
		System.out.println("Username is         : " + username);
	}
}