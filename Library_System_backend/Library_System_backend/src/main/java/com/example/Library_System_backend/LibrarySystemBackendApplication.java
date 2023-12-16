package com.example.Library_System_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.Library_System_backend.entity")
@EnableJpaRepositories("com.example.Library_System_backend.repository")
public class LibrarySystemBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystemBackendApplication.class, args);
	}

}
