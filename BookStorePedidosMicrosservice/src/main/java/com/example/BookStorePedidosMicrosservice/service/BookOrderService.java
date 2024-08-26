package com.example.BookStorePedidosMicrosservice.service;

import com.example.BookStorePedidosMicrosservice.client.BookClient;
import com.example.BookStorePedidosMicrosservice.client.CustomerClient;
import com.example.BookStorePedidosMicrosservice.model.BookOrder;
import com.example.BookStorePedidosMicrosservice.repository.BookOrderRepository;
import com.tp2DDD.ClienteMicroservice.model.Customer;
import com.tp2DDD.livroMicroService.model.Book;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookOrderService {

    @Autowired
    private BookOrderRepository bookOrderRepository;

    @Autowired
    private BookClient bookClient;

    @Autowired
    private CustomerClient customerClient;

    public List<BookOrder> getAllBookOrders() {
        return bookOrderRepository.findAll();
    }

    public Optional<BookOrder> getBookOrderById(Long id) {
        return bookOrderRepository.findById(id);
    }

    public BookOrder saveOrder(BookOrder bookOrder) {
        try {
            Book book = bookClient.getBookById(bookOrder.getBookId());

            Customer customer = customerClient.getBookById(bookOrder.getCustomerId());

            return bookOrderRepository.save(bookOrder);
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Livro ou cliente não encontrado", e);
        }

    }

    public void deleteBookOrder(Long id) {
        bookOrderRepository.deleteById(id);
    }
}