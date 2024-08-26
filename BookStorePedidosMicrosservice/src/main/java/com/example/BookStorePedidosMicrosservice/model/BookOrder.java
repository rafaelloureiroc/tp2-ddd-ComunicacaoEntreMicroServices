package com.example.BookStorePedidosMicrosservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Long bookId;

    private int quantity;

    private double totalPrice;

    public BookOrder(Long customerId, Long bookId, int quantity, double bookPrice) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice(bookPrice);
    }

    private double calculateTotalPrice(double bookPrice) {
        return this.quantity * bookPrice;
    }
}