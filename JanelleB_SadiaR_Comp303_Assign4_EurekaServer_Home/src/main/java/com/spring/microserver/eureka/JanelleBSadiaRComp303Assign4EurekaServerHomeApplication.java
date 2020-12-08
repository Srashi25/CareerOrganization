package com.spring.microserver.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class JanelleBSadiaRComp303Assign4EurekaServerHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JanelleBSadiaRComp303Assign4EurekaServerHomeApplication.class, args);
		
		System.out.println("Eureka Server Started. Please go to localhost:8761/home for the homepage");
	}

}
