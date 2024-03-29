package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OpenapiApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(OpenapiApplication.class, args);
	}
}
