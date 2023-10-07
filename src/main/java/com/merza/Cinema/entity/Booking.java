package com.merza.Cinema.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="booking_date")
    private LocalDateTime bookingDate;

    @ManyToOne
    @JsonBackReference(value = "bookings")
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @ManyToOne
    @JsonBackReference(value = "show")
    @JoinColumn(name = "show_id")
    private Show show;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_seat_id")
    private BookedSeat bookedSeat;

}
