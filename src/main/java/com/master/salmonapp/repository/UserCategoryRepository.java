package com.master.salmonapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.salmonapp.entity.UserCategory;

public interface UserCategoryRepository extends JpaRepository<UserCategory, String> {

}