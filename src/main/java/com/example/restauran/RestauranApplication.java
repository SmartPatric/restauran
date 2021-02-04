package com.example.restauran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RestauranApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranApplication.class, args);
	}

}
