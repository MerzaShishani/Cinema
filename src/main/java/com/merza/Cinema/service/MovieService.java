package com.merza.Cinema.service;

import com.merza.Cinema.dto.DailyReport;
import com.merza.Cinema.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> findAll();
    Movie findById(Long id);
    Movie save(Movie entity);
    void delete(Long id);
    Movie update(Movie movie,Long id);
    List<DailyReport> getDailyReport();
}
