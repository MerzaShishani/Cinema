package com.merza.Cinema.service;

import com.merza.Cinema.entity.BookedSeat;
import com.merza.Cinema.entity.Booking;

import java.util.List;

public interface BookedSeatService {

    List<BookedSeat> findAll();
    BookedSeat findById(Long id);
    BookedSeat save(BookedSeat bookedSeat);
    void delete(Long id);
}
