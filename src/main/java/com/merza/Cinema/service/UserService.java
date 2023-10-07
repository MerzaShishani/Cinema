package com.merza.Cinema.service;

import com.merza.Cinema.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<AppUser> findAll();
    AppUser findById(Long id);
    AppUser save(AppUser appUser);
    public Optional<AppUser> findUserByUsername(String username);
}
