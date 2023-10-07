package com.merza.Cinema.controller;

import com.merza.Cinema.dto.AppUserDto;
import com.merza.Cinema.entity.AppUser;
import com.merza.Cinema.security.AuthUserDetails;
import com.merza.Cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<AppUserDto>> findAll(){

        return ResponseEntity.ok(userService.findAll().stream().map(AppUserDto::new).toList());
    }

    @GetMapping("/profile")
    public ResponseEntity<AppUserDto> getUserProfile(@AuthenticationPrincipal AuthUserDetails user){
        AppUser appUser = userService.findById(user.getId());

        return ResponseEntity.ok(new AppUserDto(appUser));
    }
}
