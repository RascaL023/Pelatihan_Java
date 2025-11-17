package com.example.minpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@Controller
public class MinproApplication {  
	public static void main(String[] args) {
		SpringApplication.run(MinproApplication.class, args);
	}

	@GetMapping("/")
	public String landingPage(){
		return "index.html";
	}
}
