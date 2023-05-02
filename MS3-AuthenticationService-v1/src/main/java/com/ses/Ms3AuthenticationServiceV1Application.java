package com.ses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Ms3AuthenticationServiceV1Application {

	public static void main(String[] args) {
		SpringApplication.run(Ms3AuthenticationServiceV1Application.class, args);
	}

}
