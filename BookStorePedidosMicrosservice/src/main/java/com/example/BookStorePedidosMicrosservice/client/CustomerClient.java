package com.example.BookStorePedidosMicrosservice.client;

import com.tp2DDD.ClienteMicroservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CustomerMicroservice", url = "http://localhost:8083")
public interface CustomerClient {

    @GetMapping("/customers/{id}")
    Customer getBookById(@PathVariable("id") Long id);

}