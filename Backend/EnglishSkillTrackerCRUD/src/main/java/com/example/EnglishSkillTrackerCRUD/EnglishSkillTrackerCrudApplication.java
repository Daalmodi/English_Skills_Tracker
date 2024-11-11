package com.example.EnglishSkillTrackerCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EnglishSkillTrackerCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnglishSkillTrackerCrudApplication.class, args);
		System.out.println("Aplicacion Corriendo...");
	}
	public String getMethodName(@RequestParam String param) {
		return new String();
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "Daniel", defaultValue = "World") String name) {
		
		return String.format("Hello %s!", name);
	}

}
