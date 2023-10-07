package com.merza.Cinema.service.Impls;

import com.merza.Cinema.dto.BookingRequest;
import com.merza.Cinema.entity.*;
import com.merza.Cinema.exception.DuplicateRecordException;
import com.merza.Cinema.exception.ElementNotFoundException;
import com.merza.Cinema.exception.ShowAlreadyEndedException;
import com.merza.Cinema.exception.ShowIsFullException;
import com.merza.Cinema.repository.BookingRepository;
import com.merza.Cinema.service.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookedSeatService bookedSeatService;
    private final ShowService showService;
    private final SeatService seatService;
    private final UserService userService;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              @Lazy BookedSeatService bookedSeatService,
                              @Lazy ShowService showService,
                              @Lazy SeatService seatService,
                              @Lazy UserService userService) {
        this.bookingRepository = bookingRepository;
        this.bookedSeatService = bookedSeatService;
        this.showService = showService;
        this.seatService = seatService;
        this.userService = userService;
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElseThrow(()-> new ElementNotFoundException(id));
    }

    @Override
    public Booking save(BookingRequest request, Long userId) {

        Seat seat = seatService.findByColRow(request.getRowNo(), request.getColNo());

        Show show = showService.findById(request.getShowId());

        if(show.getEndTime().isBefore(LocalDateTime.now()))
            throw new ShowAlreadyEndedException();
        if ( bookedSeatService.findAll().stream().filter(b-> b.getShow().equals(show)).count() >= show.getTheater().getCapacity())
            throw new ShowIsFullException(show.getId());
        if (bookedSeatService.findAll().stream().anyMatch(b-> b.getSeat().getId() == seat.getId() && b.getShow().getId() == show.getId())){
            throw new DuplicateRecordException("The seat is already booked!");
        }

        BookedSeat bookedSeat = new BookedSeat();
        bookedSeat.setSeat(seat);
        bookedSeat.setShow(show);

        BookedSeat savedBookedSeat = bookedSeatService.save(bookedSeat);

        AppUser appUser = userService.findById(userId);

        Booking booking = new Booking();
        booking.setBookingDate(LocalDateTime.now());
        booking.setBookedSeat(savedBookedSeat);
        booking.setShow(show);
        booking.setAppUser(appUser);
        return bookingRepository.save(booking);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.delete(findById(id));
    }

    @Override
    public List<Booking> getBookingsByMovieId(Long movieId){
        return findAll().stream().filter(b -> b.getShow().getMovie().getId().equals(movieId)).toList();
    }

    @Override
    public List<Seat> availableSeats(Long showId){

        List<BookedSeat> bookedSeats = bookedSeatService.findAll().stream()
                .filter(bookedSeat -> bookedSeat.getShow().getId().equals(showId)).toList();
        List<Seat> seats = showService.findById(showId).getTheater().getSeats();

        List<Seat> availableSeats = seats.stream().
                filter(seat -> bookedSeats.stream().noneMatch(bs-> seat.getId() == bs.getSeat().getId())).toList();

        return availableSeats;
    }
}

