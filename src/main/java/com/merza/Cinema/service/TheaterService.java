package com.merza.Cinema.service;

import com.merza.Cinema.entity.Theater;

import java.util.List;

public interface TheaterService {

    List<Theater> findAll();
    Theater findById(Long id);
    Theater save(Theater entity);
    void delete(Long id);
}
