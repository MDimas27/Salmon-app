package com.master.salmonapp.repository;

import com.master.salmonapp.entity.InsentifView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InsentifViewRepository extends JpaRepository<InsentifView, Integer> {

}
