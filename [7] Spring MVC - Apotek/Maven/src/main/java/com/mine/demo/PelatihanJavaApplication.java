package com.mine.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mine")
public class PelatihanJavaApplication {
	public static void main(String[] args) {
		SpringApplication.run(PelatihanJavaApplication.class, args);
	}

	@RestController
	class test{
		@GetMapping("/")
		public String greet(){ return "List Obat => .../obat/list"; }
	}
}
