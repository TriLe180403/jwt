package com.example.Security;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class SecurityApplication {
	private final Logger logger = (Logger) LoggerFactory.getLogger(SecurityApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}
