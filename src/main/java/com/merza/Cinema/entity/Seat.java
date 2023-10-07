package com.merza.Cinema.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Integer rowNo;

    private Integer colNo;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "theater_id",referencedColumnName="id")
    private Theater theater;

//    @ManyToOne
//    @JsonBackReference(value = "seats")
//    @JoinColumn(name = "show_id")
//    private Show show;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<BookedSeat> bookedSeats;

}
