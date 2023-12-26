package com.nephew.faqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.*;

@SpringBootApplication
public class FaqsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaqsServiceApplication.class, args);
	}

}
