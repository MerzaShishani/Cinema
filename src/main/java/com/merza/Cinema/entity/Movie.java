package com.merza.Cinema.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name="duration")
    private Integer duration;

    @Column(name="release_date")
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonManagedReference(value = "shows")
    private List<Show> shows;

}
