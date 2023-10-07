package com.merza.Cinema.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name="theater")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "capacity")
    private int capacity;

    @JsonManagedReference(value = "theater")
    @OneToMany(mappedBy = "theater",fetch = FetchType.LAZY)
    private List<Show> shows;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Seat> seats;
}
