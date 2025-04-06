package com.github.filipe.desafioapi.services;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CrudService<ID, T> {
    Page<T> findAllPaged(Integer page, Integer size);
    T findById(ID id);
    T create(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}
