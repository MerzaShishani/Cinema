package com.merza.Cinema.dto;

import com.merza.Cinema.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {

    private Long id;

    private String username;

    private List<BookingDto> bookings;

    public AppUserDto(AppUser appUser){
        this.id = appUser.getId();
        this.username= appUser.getUsername();
        this.bookings = appUser.getBookings().stream().map(BookingDto::new).toList();

    }

}
