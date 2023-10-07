package com.merza.Cinema.dto;

import com.merza.Cinema.entity.BookedSeat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookedSeatDto {

    private Long id;

    private SeatDto seat;

    private ShowDto show;

    public BookedSeatDto(BookedSeat bookedSeat) {
        this.id = bookedSeat.getId();
        this.seat = new SeatDto(bookedSeat.getSeat());
        this.show = new ShowDto(
                bookedSeat.getShow().getId(),
                bookedSeat.getShow().getStartTime(),
                bookedSeat.getShow().getEndTime(),
                null,
                null,
                null
        );
    }
}
