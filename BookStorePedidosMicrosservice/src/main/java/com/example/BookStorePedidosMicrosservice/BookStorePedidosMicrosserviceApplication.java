package com.example.BookStorePedidosMicrosservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookStorePedidosMicrosserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookStorePedidosMicrosserviceApplication.class, args);
	}

}
