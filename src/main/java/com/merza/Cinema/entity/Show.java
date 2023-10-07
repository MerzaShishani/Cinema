package com.merza.Cinema.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    @JsonManagedReference(value = "show")
    private List<BookedSeat> bookedSeats = new ArrayList<>();

    @ManyToOne
    @JsonBackReference(value = "shows")
    @JoinColumn(name="movie_id")
    private Movie movie;

    @ManyToOne
    @JsonBackReference(value = "theater")
    @JoinColumn(name = "theater_id")
    private Theater theater;

}
