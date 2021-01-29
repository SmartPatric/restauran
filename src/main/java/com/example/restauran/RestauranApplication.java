package com.example.restauran;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@SpringBootApplication(exclude= { SecurityAutoConfiguration.class })
public class RestauranApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranApplication.class, args);
	}

}
