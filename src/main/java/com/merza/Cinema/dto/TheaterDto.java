package com.merza.Cinema.dto;

import com.merza.Cinema.entity.Theater;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TheaterDto {

    private Long id;

    private int capacity;

    private List<ShowDto> shows;

    private List<SeatDto> seats;

    public TheaterDto(Theater theater) {
        this.id = theater.getId();
        this.capacity = theater.getCapacity();
        this.seats = theater.getSeats().stream().map(seat -> new SeatDto(
                seat.getId(),
                seat.getRowNo(),
                seat.getColNo(),
                seat.getPrice(),
                null,
                null
        )).toList();
        this.shows = theater.getShows().stream().map(show -> new ShowDto(
                show.getId(),
                show.getStartTime(),
                show.getEndTime(),
                null,
                null,
                null
        )).collect(Collectors.toList());
    }
}
