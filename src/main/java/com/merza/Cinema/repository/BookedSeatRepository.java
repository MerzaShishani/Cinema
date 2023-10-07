package com.merza.Cinema.repository;

import com.merza.Cinema.entity.BookedSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookedSeatRepository extends JpaRepository<BookedSeat,Long> {
}
