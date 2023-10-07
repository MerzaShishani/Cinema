package com.merza.Cinema.dto;

import com.merza.Cinema.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private Long id;

    private LocalDateTime bookingDate;

    private Long userId;

    private String username;

    private Long seatId;

    private int rowNo;

    private int colNo;

    private double price;

    private ShowDto show;


    public BookingDto(Booking booking) {
        this.id = booking.getId();
        this.bookingDate = booking.getBookingDate();
        this.userId = booking.getAppUser().getId();
        this.username = booking.getAppUser().getUsername();
        this.seatId = booking.getBookedSeat().getSeat().getId();
        this.rowNo = booking.getBookedSeat().getSeat().getRowNo();
        this.colNo = booking.getBookedSeat().getSeat().getColNo();
        this.price = booking.getBookedSeat().getSeat().getPrice();
        this.show = new ShowDto(booking.getShow());
        this.show.setBookedSeats(null);
    }
}
