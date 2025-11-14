package com.example.service2_OrderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Service2OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Service2OrderServiceApplication.class, args);
	}

}
