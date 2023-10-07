package com.merza.Cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieStatisticsDto {

    private Long movieId;
    private String movieTitle;
    private int totalBookings;
    private double totalIncome;

}
