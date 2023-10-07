package com.merza.Cinema.dto;

import com.merza.Cinema.entity.Genre;
import com.merza.Cinema.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private Long id;

    private String title;

    private String description;

    private Genre genre;

    private Integer duration;

    private LocalDate releaseDate;

    private List<ShowDto> shows;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.genre = movie.getGenre();
        this.duration = movie.getDuration();
        this.releaseDate = movie.getReleaseDate();
        this.shows = movie.getShows().stream().map(show ->
                new ShowDto(
                        show.getId(),
                        show.getStartTime(),
                        show.getEndTime(),
                        null,
                        null,
                        null
                )).toList();
    }
}
