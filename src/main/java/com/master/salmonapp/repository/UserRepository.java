package com.master.salmonapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.salmonapp.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
    
    User findByEmail (String email);
}
