package com.merza.Cinema.service;

import com.merza.Cinema.dto.ShowRequest;
import com.merza.Cinema.entity.Show;

import java.util.List;

public interface ShowService {

    List<Show> findAll();
    Show findById(Long id);
    Show save(Show entity);
    void delete(Long id);
    Show update(ShowRequest request,Long showId);
}
