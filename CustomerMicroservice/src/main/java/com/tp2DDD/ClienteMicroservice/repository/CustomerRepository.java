package com.tp2DDD.ClienteMicroservice.repository;


import com.tp2DDD.ClienteMicroservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
