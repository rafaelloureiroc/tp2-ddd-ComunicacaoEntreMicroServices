package com.example.BookStorePedidosMicrosservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.tp2DDD.livroMicroService.model.Book;

@FeignClient(name = "bookMicroService", url = "http://localhost:8082")
public interface BookClient {

    @GetMapping("/books/{id}")
    Book getBookById(@PathVariable("id") Long id);

}