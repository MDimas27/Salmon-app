package com.master.salmonapp.service;

import java.util.List;

public interface ViewService<T, ID> {

    T saveOrUpdate(T entity);

    List<T> findAll();

    T findById(ID id);

    
}
