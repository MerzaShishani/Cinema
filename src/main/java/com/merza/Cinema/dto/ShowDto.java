package com.merza.Cinema.dto;

import com.merza.Cinema.entity.Show;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShowDto {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private List<BookedSeatDto> bookedSeats;

    private MovieDto movie;

    private TheaterDto theater;


    public ShowDto(Show show) {
        this.id = show.getId();
        this.startTime = show.getStartTime();
        this.endTime = show.getEndTime();
        this.bookedSeats = show.getBookedSeats().stream().map(BookedSeatDto::new).toList();
        this.movie = new MovieDto(show.getMovie());
        this.theater =new TheaterDto(show.getTheater());
        this.movie.setShows(null);
        this.theater.setShows(null);
        this.theater.setSeats(null);
    }
}
