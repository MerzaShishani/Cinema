package com.merza.Cinema.dto;

import com.merza.Cinema.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {

    private Long id;

    private Integer rowNo;

    private Integer colNo;

    private Double price;

    private TheaterDto theater;

    private List<BookedSeatDto> bookedSeats;

    public SeatDto(Seat seat) {
        this.id = seat.getId();
        this.rowNo = seat.getRowNo();
        this.colNo = seat.getColNo();
        this.price = seat.getPrice();
        this.theater = new TheaterDto(seat.getTheater());
        this.theater.setSeats(null);
        this.theater.setShows(null);
    }
}
