package com.baixiang;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

//@EnableAutoConfiguration
//@EnableSpringConfigured
@SpringBootApplication
//public class BaixiangApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(BaixiangApplication.class, args);
//	}
//}


public class BaixiangApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BaixiangApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BaixiangApplication.class, args);
	}

}