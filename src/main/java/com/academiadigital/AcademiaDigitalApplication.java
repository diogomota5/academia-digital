package com.academiadigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.academiadigital.Repository")
@SpringBootApplication
public class AcademiaDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademiaDigitalApplication.class, args);
	}

}
