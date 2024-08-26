package com.tp2DDD.livroMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMicroServiceApplication.class, args);
	}

}
