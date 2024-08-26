package com.tp2DDD.livroMicroService.repository;


import com.tp2DDD.livroMicroService.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
