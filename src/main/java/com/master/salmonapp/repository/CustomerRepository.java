package com.master.salmonapp.repository;

import com.master.salmonapp.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String>{
    
}
