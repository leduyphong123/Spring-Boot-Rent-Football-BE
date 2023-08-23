package com.example.football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class FootballApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
	}

}
