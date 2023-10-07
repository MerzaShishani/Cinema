package com.merza.Cinema.controller;

import com.merza.Cinema.dto.DailyReport;
import com.merza.Cinema.dto.MovieDto;
import com.merza.Cinema.dto.MovieStatisticsDto;
import com.merza.Cinema.entity.BookedSeat;
import com.merza.Cinema.entity.Movie;
import com.merza.Cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/movies")
@RequiredArgsConstructor
public class MoviesController {

    private final MovieService movieService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<MovieDto>> findAll(){
        var result = movieService.findAll().stream()
                .map(MovieDto::new).toList();
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> findById(@PathVariable Long movieId){
        var result = movieService.findById(movieId);
        return ResponseEntity.ok(new MovieDto(result));
    }

    @GetMapping("/available")
    public ResponseEntity<List<MovieDto>> findAvailable(){

        var result = movieService.findAll().stream().filter(m-> m.getShows().stream().anyMatch(s->s.getStartTime().isAfter(LocalDateTime.now())))
                .map(MovieDto::new).toList();
        result.forEach(m -> m.setShows(m.getShows().stream().filter(s->s.getStartTime().isAfter(LocalDateTime.now())).toList()));

        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto){

        Movie newMovie =movieService.save(new Movie(
                null,
                movieDto.getTitle(),
                movieDto.getDescription(),
                movieDto.getGenre(),
                movieDto.getDuration(),
                movieDto.getReleaseDate(),
                new ArrayList<>()
        ));

        return ResponseEntity.ok(new MovieDto(newMovie));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto movieDto,@PathVariable Long id){

        Movie updatedMovie =new Movie();
        BeanUtils.copyProperties(movieDto,updatedMovie);

        var movie = movieService.update(updatedMovie,id);

        return ResponseEntity.ok(new MovieDto(movie));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void delete(@PathVariable long id){
        movieService.delete(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/statistics/{movieId}")
    public ResponseEntity<MovieStatisticsDto> statistics(@PathVariable Long movieId){

        Movie movie = movieService.findById(movieId);

        List<BookedSeat> bookedSeats = movie.getShows().stream().flatMap(s -> s.getBookedSeats().stream()).toList();

        double income = bookedSeats.stream().mapToDouble(bookedSeat -> bookedSeat.getSeat().getPrice()).sum();

        MovieStatisticsDto statisticsDto = new MovieStatisticsDto();
        statisticsDto.setMovieId(movieId);
        statisticsDto.setMovieTitle(movie.getTitle());
        statisticsDto.setTotalIncome(income);
        statisticsDto.setTotalBookings(bookedSeats.size());

        return ResponseEntity.ok(statisticsDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dailyreport")
    public ResponseEntity<List<DailyReport>> statistics(){

        return ResponseEntity.ok(movieService.getDailyReport());
    }
}
