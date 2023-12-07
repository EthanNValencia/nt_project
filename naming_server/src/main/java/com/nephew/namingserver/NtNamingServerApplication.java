package com.nephew.namingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NtNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NtNamingServerApplication.class, args);
	}

}
