package com.gempel.app.rest;

import com.gempel.app.rest.GUI.Layout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(RestApiApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run();
		//SpringApplication.run(RestApiApplication.class, args);
		Layout layout = new Layout();
		layout.start();
	}

}
