package com.ses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SesApplication.class, args);
	}

}
