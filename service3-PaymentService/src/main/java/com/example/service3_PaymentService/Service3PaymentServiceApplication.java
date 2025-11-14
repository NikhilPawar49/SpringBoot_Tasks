package com.example.service3_PaymentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Service3PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Service3PaymentServiceApplication.class, args);
	}

}
