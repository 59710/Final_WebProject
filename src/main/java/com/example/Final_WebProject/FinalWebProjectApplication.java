package com.example.Final_WebProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class FinalWebProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalWebProjectApplication.class, args);
	}

}
