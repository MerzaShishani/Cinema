package com.merza.Cinema.service.Impls;

import com.merza.Cinema.entity.BookedSeat;
import com.merza.Cinema.exception.ElementNotFoundException;
import com.merza.Cinema.repository.BookedSeatRepository;
import com.merza.Cinema.service.BookedSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookedSeatServiceImpl implements BookedSeatService {

    private final BookedSeatRepository bookedSeatRepository;

    @Override
    public List<BookedSeat> findAll() {
        return bookedSeatRepository.findAll();
    }

    @Override
    public BookedSeat findById(Long id) {
        return bookedSeatRepository.findById(id).orElseThrow(()-> new ElementNotFoundException(id));
    }

    @Override
    public BookedSeat save(BookedSeat bookedSeat) {
        return bookedSeatRepository.save(bookedSeat);
    }

    @Override
    public void delete(Long id) {
        bookedSeatRepository.delete(findById(id));
    }
}
