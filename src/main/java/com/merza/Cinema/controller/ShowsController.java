package com.merza.Cinema.controller;

import com.merza.Cinema.dto.ShowDto;
import com.merza.Cinema.dto.ShowRequest;
import com.merza.Cinema.entity.Movie;
import com.merza.Cinema.entity.Show;
import com.merza.Cinema.entity.Theater;
import com.merza.Cinema.exception.DuplicateRecordException;
import com.merza.Cinema.service.MovieService;
import com.merza.Cinema.service.ShowService;
import com.merza.Cinema.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shows")
@RequiredArgsConstructor
public class ShowsController {

    private final ShowService showService;
    private final MovieService movieService;
    private final TheaterService theaterService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ShowDto>> findAll(){

        var result = showService.findAll().stream()
                .map(ShowDto::new)
                .toList();
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ShowDto> addShow(@RequestBody ShowRequest request) {
        Movie movie = movieService.findById(request.getMovieId());
        Theater theater = theaterService.findById(request.getTheaterId());

        if (theater.getShows().stream().anyMatch(s-> request.getStartTime().isBefore(s.getEndTime())
                && s.getStartTime().isBefore(request.getEndTime())))
            throw new DuplicateRecordException("there is already a show in selected time");
        else{
            Show show = new Show();
            show.setStartTime(request.getStartTime());
            show.setEndTime(request.getEndTime());
            show.setMovie(movie);
            show.setTheater(theater);

            var savedShow = showService.save(show);
            return ResponseEntity.ok(new ShowDto(savedShow));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{showId}")
    public ResponseEntity<ShowDto> updateShow(@RequestBody ShowRequest request, @PathVariable Long showId){

        var show = showService.update(request,showId);

        return ResponseEntity.ok(new ShowDto(show));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void delete(@PathVariable long id){
        showService.delete(id);
    }
}
