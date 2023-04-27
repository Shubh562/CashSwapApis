package com.eztrans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EztransApplication {

	public static void main(String[] args) {
		SpringApplication.run(EztransApplication.class, args);
	}

}
