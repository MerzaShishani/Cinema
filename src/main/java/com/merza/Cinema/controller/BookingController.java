package com.merza.Cinema.controller;


import com.merza.Cinema.dto.BookingDto;
import com.merza.Cinema.dto.BookingRequest;
import com.merza.Cinema.dto.SeatDto;
import com.merza.Cinema.entity.Booking;
import com.merza.Cinema.entity.Seat;
import com.merza.Cinema.security.AuthUserDetails;
import com.merza.Cinema.service.BookingService;
import com.merza.Cinema.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final SeatService seatService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookingDto>> findAll(){

        var result = bookingService.findAll().stream().map(BookingDto::new).toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<BookingDto> addBooking(@RequestBody BookingRequest request, @AuthenticationPrincipal AuthUserDetails user){

        Booking savedBooking = bookingService.save(request,user.getId());

        return ResponseEntity.ok(new BookingDto(savedBooking));
    }

    @PostMapping("/add/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookingDto> AdminBooking(@RequestBody BookingRequest request, @PathVariable Long userId){

        Booking savedBooking = bookingService.save(request,userId);

        return ResponseEntity.ok(new BookingDto(savedBooking));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId){

        bookingService.delete(bookingId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/price")
    public ResponseEntity<SeatDto> getPrice(@RequestBody BookingRequest request){

        Seat seat = seatService.findByColRow(request.getRowNo(), request.getColNo());

        SeatDto seatDto = new SeatDto();
        seatDto.setRowNo(seat.getRowNo());
        seatDto.setColNo(seat.getColNo());
        seatDto.setPrice(seat.getPrice());

        return ResponseEntity.ok(seatDto);
    }

    @GetMapping("/available/{showId}")
    public ResponseEntity<List<SeatDto>> availableSeats(@PathVariable Long showId) {

        var result = bookingService.availableSeats(showId).stream().map(SeatDto::new).toList();

        return ResponseEntity.ok(result);
    }
}
