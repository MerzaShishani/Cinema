package com.merza.Cinema.config;

import com.merza.Cinema.entity.*;
import com.merza.Cinema.service.*;
import com.merza.Cinema.service.Impls.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class StartupApp implements CommandLineRunner {

    @Value("${price.premium}")
    private Double premium;

    @Value("${price.classic}")
    private Double classic;

    private final UserServiceImpl userService;

    private final RoleService roleService;

    private final TheaterService theaterService;

    private final MovieService movieService;
    private final ShowService showService;
    private final SeatService seatService;


    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {


        if (roleService.findAll().isEmpty()) {
            roleService.save(new Role(null, "ADMIN"));
            roleService.save(new Role(null, "USER"));
        }



        if (theaterService.findAll().isEmpty()){
            theaterService.save(new Theater(null,64,null,null));
            theaterService.save(new Theater(null,64,null,null));

        }

        if (seatService.findAll().isEmpty()){
            Theater theater1 = theaterService.findById(1L);
            // to fill the seats for each theater, in real world we fill them as we want.
            for ( int i = 1 ; i<=8; i++){
                for (int j=1; j<=8; j++){
                    double price = i==1? premium:classic;
                    theater1.getSeats().add(new Seat(null,i,j,price,theater1,null));
                }
            }
            theaterService.save(theater1);

            Theater theater2 = theaterService.findById(2L);
            // to fill the seats for each theater, in real world we fill them as we want.
            for ( int i = 1 ; i<=8; i++){
                for (int j=1; j<=8; j++){
                    double price = i==1? premium:classic;
                    theater2.getSeats().add(new Seat(null,i,j,price,theater2,null));
                }
            }
            theaterService.save(theater2);
        }

        if (movieService.findAll().isEmpty()){
            movieService.save(new Movie(null,"Taken","Taken Movie", Genre.ACTION,119,LocalDate.of(2015,7,1),new ArrayList<>()));
            movieService.save(new Movie(null,"Titanic","Titanic Movie", Genre.DRAMA,102, LocalDate.of(2005,1,1),new ArrayList<>()));
        }

        if (showService.findAll().isEmpty()) {

            showService.save(new Show(null, LocalDateTime.of(2023,9,25,20,0),
                    LocalDateTime.of(2023,9,25,22, 0),
                    new ArrayList<>(), movieService.findById(1L),theaterService.findById(1L)));
            showService.save(new Show(null, LocalDateTime.of(2023,9,25,16,0),
                    LocalDateTime.of(2023,9,25,18, 0),
                    new ArrayList<>(),movieService.findById(2L),theaterService.findById(1L)));
        }


        if (userService.findAll().isEmpty()) {

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(roleService.findByName("ADMIN"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(roleService.findByName("USER"));

            userService.save(new AppUser(null,"merza", passwordEncoder.encode("merza"), adminRoles,new ArrayList<>(), true, true, true, true));
            userService.save(new AppUser(null,"khaled", passwordEncoder.encode("khaled"), userRoles,new ArrayList<>(),  true, true, true, true));
            userService.save(new AppUser(null, "mohammad", passwordEncoder.encode("mohammad"), userRoles,new ArrayList<>(),  true, true, true, true));
        }
    }
}
