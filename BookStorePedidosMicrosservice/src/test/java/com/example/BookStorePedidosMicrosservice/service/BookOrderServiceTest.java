package com.example.BookStorePedidosMicrosservice.service;

import com.example.BookStorePedidosMicrosservice.client.BookClient;
import com.example.BookStorePedidosMicrosservice.client.CustomerClient;
import com.example.BookStorePedidosMicrosservice.model.BookOrder;
import com.example.BookStorePedidosMicrosservice.repository.BookOrderRepository;
import com.tp2DDD.ClienteMicroservice.model.Customer;
import com.tp2DDD.livroMicroService.model.Book;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookOrderServiceTest {

    @InjectMocks
    private BookOrderService bookOrderService;

    @Mock
    private BookOrderRepository bookOrderRepository;

    @Mock
    private BookClient bookClient;

    @Mock
    private CustomerClient customerClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveOrder_Success() {
        Book book = new Book();
        book.setId(1L);
        book.setPrice(100.0);

        Customer customer = new Customer();
        customer.setId(1L);

        BookOrder bookOrder = new BookOrder();
        bookOrder.setBookId(1L);
        bookOrder.setCustomerId(1L);
        bookOrder.setQuantity(2);
        bookOrder.setTotalPrice(200.0);

        when(bookClient.getBookById(1L)).thenReturn(book);
        when(customerClient.getBookById(1L)).thenReturn(customer);
        when(bookOrderRepository.save(any(BookOrder.class))).thenReturn(bookOrder);

        BookOrder result = bookOrderService.saveOrder(bookOrder);

        assertEquals(1L, result.getBookId());
        assertEquals(1L, result.getCustomerId());
        assertEquals(200.0, result.getTotalPrice());
        verify(bookOrderRepository, times(1)).save(bookOrder);
    }

    @Test
    public void testSaveOrder_BookNotFound() {
        BookOrder bookOrder = new BookOrder();
        bookOrder.setBookId(1L);
        bookOrder.setCustomerId(1L);

        when(bookClient.getBookById(1L)).thenThrow(FeignException.NotFound.class);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookOrderService.saveOrder(bookOrder);
        });

        assertEquals("Livro ou cliente não encontrado", exception.getMessage());
        verify(bookOrderRepository, never()).save(any(BookOrder.class));
    }

    @Test
    public void testSaveOrder_CustomerNotFound() {
        Book book = new Book();
        book.setId(1L);
        book.setPrice(100.0);

        BookOrder bookOrder = new BookOrder();
        bookOrder.setBookId(1L);
        bookOrder.setCustomerId(1L);

        when(bookClient.getBookById(1L)).thenReturn(book);
        when(customerClient.getBookById(1L)).thenThrow(FeignException.NotFound.class);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookOrderService.saveOrder(bookOrder);
        });

        assertEquals("Livro ou cliente não encontrado", exception.getMessage());
        verify(bookOrderRepository, never()).save(any(BookOrder.class));
    }

    @Test
    public void testSaveOrder_CustomerExistsButBookNotFound() {
        Customer customer = new Customer();
        customer.setId(1L);

        BookOrder bookOrder = new BookOrder();
        bookOrder.setBookId(1L);
        bookOrder.setCustomerId(1L);

        when(customerClient.getBookById(1L)).thenReturn(customer);
        when(bookClient.getBookById(1L)).thenThrow(FeignException.NotFound.class);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookOrderService.saveOrder(bookOrder);
        });

        assertEquals("Livro ou cliente não encontrado", exception.getMessage());
        verify(bookOrderRepository, never()).save(any(BookOrder.class));
    }

    @Test
    public void testSaveOrder_BookExistsButCustomerNotFound() {
        Book book = new Book();
        book.setId(1L);
        book.setPrice(100.0);

        BookOrder bookOrder = new BookOrder();
        bookOrder.setBookId(1L);
        bookOrder.setCustomerId(1L);

        when(bookClient.getBookById(1L)).thenReturn(book);
        when(customerClient.getBookById(1L)).thenThrow(FeignException.NotFound.class);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookOrderService.saveOrder(bookOrder);
        });

        assertEquals("Livro ou cliente não encontrado", exception.getMessage());
        verify(bookOrderRepository, never()).save(any(BookOrder.class));
    }
}