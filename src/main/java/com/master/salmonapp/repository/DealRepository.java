package com.master.salmonapp.repository;

import com.master.salmonapp.entity.Deal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, String> {
    
}
