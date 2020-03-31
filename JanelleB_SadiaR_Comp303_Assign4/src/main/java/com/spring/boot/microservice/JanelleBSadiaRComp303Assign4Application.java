package com.spring.boot.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class JanelleBSadiaRComp303Assign4Application {

	public static void main(String[] args) {
		SpringApplication.run(JanelleBSadiaRComp303Assign4Application.class, args);
		System.out.println("Spring Boot JPA Eureka server started");
	}

}
