package com.master.salmonapp.repository;

import com.master.salmonapp.entity.Team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {

    
}
