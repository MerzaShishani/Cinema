package com.merza.Cinema.controller;

import com.merza.Cinema.dto.SeatDto;
import com.merza.Cinema.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/seats")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class SeatsController {

    private final SeatService seatService;

    @GetMapping
    public ResponseEntity<List<SeatDto>> findAll(){
        var result = seatService.findAll().stream().map(SeatDto::new).toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{seatId}")
    public ResponseEntity<SeatDto> findById(@PathVariable Long seatId){
        var result = seatService.findById(seatId);
        return ResponseEntity.ok(new SeatDto(result));
    }

}
