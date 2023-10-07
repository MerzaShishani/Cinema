package com.merza.Cinema.service.Impls;

import com.merza.Cinema.entity.Theater;
import com.merza.Cinema.exception.ElementNotFoundException;
import com.merza.Cinema.repository.TheaterRepository;
import com.merza.Cinema.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;

    @Override
    public List<Theater> findAll() {
        return theaterRepository.findAll();
    }

    @Override
    public Theater findById(Long id) {
        return theaterRepository.findById(id).orElseThrow(() ->new ElementNotFoundException(id));
    }

    @Override
    public Theater save(Theater entity) {
        return theaterRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        theaterRepository.delete(findById(id));
    }
}
