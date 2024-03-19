package com.example.Security;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@ImportResource("classpath:web.xml")



@SpringBootApplication
public class SecurityApplication {


	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}
