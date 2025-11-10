package com.example.electionpollapplication.data.repository;

import java.util.List;

public interface Repository<T>{
    List<T> findAll();
    T findOne(Long id);
    T createOne(T data);

    T update(Long id, T data);
    void deleteOne(Long id);
}
