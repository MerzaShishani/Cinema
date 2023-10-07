package com.merza.Cinema.service;

import com.merza.Cinema.dto.BookingRequest;
import com.merza.Cinema.entity.Booking;
import com.merza.Cinema.entity.Seat;

import java.util.List;

public interface BookingService {

    List<Booking> findAll();
    Booking findById(Long id);
    Booking save(BookingRequest request, Long userId);
    void delete(Long id);
    List<Booking> getBookingsByMovieId(Long movieId);
    List<Seat> availableSeats(Long showId);
}
