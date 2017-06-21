package com.baixiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSpringConfigured
public class BaixiangApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaixiangApplication.class, args);
	}
}
