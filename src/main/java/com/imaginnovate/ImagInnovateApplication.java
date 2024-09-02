package com.imaginnovate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ImagInnovateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImagInnovateApplication.class, args);
	}

}
