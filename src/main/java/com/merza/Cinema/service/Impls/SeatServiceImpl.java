package com.merza.Cinema.service.Impls;

import com.merza.Cinema.entity.Seat;
import com.merza.Cinema.exception.ElementNotFoundException;
import com.merza.Cinema.repository.SeatRepository;
import com.merza.Cinema.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {


    @Value("${price.premium}")
    private Double premium;

    @Value("${price.classic}")
    private Double classic;

    private final SeatRepository seatRepository;

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public Seat findById(Long id) {
        return seatRepository.findById(id).orElseThrow( ()-> new ElementNotFoundException("Seat not found id: " + id));
    }

    @Override
    public Seat save(Seat seat) {
//        if (findAll().stream().anyMatch(s -> Objects.equals(s.getShow().getId(), seat.getShow().getId())))
//            if (findAll().stream().anyMatch(s->(s.getColNo().equals(seat.getColNo()) && s.getRowNo().equals(seat.getRowNo()))) )
//                throw new DuplicateRecordException("the seat is already booked");
        return seatRepository.save(seat);
    }

    @Override
    public void delete(Long id) {
        seatRepository.delete(findById(id));
    }

    public Seat findByColRow(int row, int col){
        return seatRepository.findAll().stream().filter(s-> s.getRowNo() == row && s.getColNo() == col).findAny()
                .orElseThrow(()->new ElementNotFoundException("Entered seat does not exist"));
    }
}
