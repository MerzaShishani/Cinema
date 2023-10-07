package com.merza.Cinema.service;

import com.merza.Cinema.entity.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatService {

    List<Seat> findAll();
    Seat findById(Long id);
    Seat save(Seat entity);
    void delete(Long id);
    Seat findByColRow(int row, int col);
}
