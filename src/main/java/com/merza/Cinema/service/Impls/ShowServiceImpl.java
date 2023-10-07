package com.merza.Cinema.service.Impls;

import com.merza.Cinema.dto.ShowRequest;
import com.merza.Cinema.entity.Show;
import com.merza.Cinema.entity.Theater;
import com.merza.Cinema.exception.DuplicateRecordException;
import com.merza.Cinema.exception.ElementNotFoundException;
import com.merza.Cinema.exception.InvalidDeleteException;
import com.merza.Cinema.repository.ShowRepository;
import com.merza.Cinema.service.BookedSeatService;
import com.merza.Cinema.service.MovieService;
import com.merza.Cinema.service.ShowService;
import com.merza.Cinema.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final TheaterService theaterService;
    private final MovieService movieService;
    private final BookedSeatService bookedSeatService;

    @Override
    public List<Show> findAll() {
        return showRepository.findAll();
    }

    @Override
    public Show findById(Long id) {
        return showRepository.findById(id).orElseThrow(()-> new ElementNotFoundException("Show does not exist id: "+ id));
    }

    @Override
    public Show save(Show entity) {
        return showRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (!findById(id).getBookedSeats().isEmpty())
            throw new InvalidDeleteException("can not be deleted! some seats has been already booked in this show");
        showRepository.delete(findById(id));
    }

    @Override
    public Show update(ShowRequest request, Long id) {

        Theater theater;

        if(request.getTheaterId() != null) {
            theater = theaterService.findById(request.getTheaterId());
            if (theater.getShows().stream().anyMatch(s-> request.getStartTime().isBefore( s.getEndTime())
                    && request.getEndTime().isAfter(s.getStartTime())))
                throw new DuplicateRecordException("there is already a show in selected time");
        }

        Show show = findById(id);

        if (request.getStartTime() != null)
            show.setStartTime(request.getStartTime());
        if (request.getEndTime() != null)
            show.setEndTime(request.getEndTime());
        if (request.getMovieId() != null)
            show.setMovie(movieService.findById(request.getMovieId()));
        if (request.getTheaterId() != null)
            show.setTheater(theaterService.findById(request.getTheaterId()));

        return save(show);
    }


}
