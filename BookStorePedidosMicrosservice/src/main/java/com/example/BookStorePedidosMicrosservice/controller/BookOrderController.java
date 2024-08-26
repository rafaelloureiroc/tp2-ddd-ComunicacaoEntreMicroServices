package com.example.BookStorePedidosMicrosservice.controller;

import com.example.BookStorePedidosMicrosservice.model.BookOrder;
import com.example.BookStorePedidosMicrosservice.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class BookOrderController {

    @Autowired
    private BookOrderService bookOrderService;

    @GetMapping
    public List<BookOrder> getAllOrders() {
        return bookOrderService.getAllBookOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookOrder> getBookOrderById(@PathVariable Long id) {
        Optional<BookOrder> bookOrder = bookOrderService.getBookOrderById(id);
        if (bookOrder.isPresent()) {
            return ResponseEntity.ok(bookOrder.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public BookOrder createBookOrder(@RequestBody BookOrder bookOrder) {
        return bookOrderService.saveOrder(bookOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookOrder(@PathVariable Long id) {
        bookOrderService.deleteBookOrder(id);
        return ResponseEntity.noContent().build();
    }
}
