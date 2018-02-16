package com.techm.transport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransportApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportApplication.class, args);
		//
	}
	

	@Bean
	public SimpleCORSFilter corsFilter(){
		return new SimpleCORSFilter();
	}
}
