package com.merza.Cinema.service.Impls;

import com.merza.Cinema.dto.DailyReport;
import com.merza.Cinema.entity.Movie;
import com.merza.Cinema.exception.ElementNotFoundException;
import com.merza.Cinema.repository.MovieRepository;
import com.merza.Cinema.service.BookingService;
import com.merza.Cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final BookingService bookingService;


    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() ->new ElementNotFoundException(id));
    }

    @Override
    public Movie save(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        movieRepository.delete(findById(id));
    }

    @Override
    public Movie update(Movie updatedMovie, Long id) {
        Movie movie = findById(id);

        if(updatedMovie.getTitle() != null)
            movie.setTitle(updatedMovie.getTitle());
        if(updatedMovie.getDescription() != null)
            movie.setDescription(updatedMovie.getDescription());
        if(updatedMovie.getGenre() != null)
            movie.setGenre(updatedMovie.getGenre());
        if(updatedMovie.getDuration() != null)
            movie.setDuration(updatedMovie.getDuration());
        if(updatedMovie.getReleaseDate() != null)
            movie.setReleaseDate(updatedMovie.getReleaseDate());

        return save(movie);
    }

    @Override
    public List<DailyReport> getDailyReport(){

        var bookings = bookingService.findAll().stream()
                .filter(b -> b.getBookingDate().toLocalDate().equals(LocalDate.now())).toList();

        List<DailyReport> reportList = new ArrayList<>();
        bookings.stream()
                .collect(Collectors.groupingBy(booking -> booking.getShow().getMovie(),
                        Collectors.summingDouble(b-> b.getBookedSeat().getSeat().getPrice())))
                .forEach((movie,sum)-> reportList.add(new DailyReport(movie.getId(), movie.getTitle(), sum)));

        return reportList;
    }

}
