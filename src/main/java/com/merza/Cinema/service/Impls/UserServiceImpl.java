package com.merza.Cinema.service.Impls;

import com.merza.Cinema.entity.AppUser;
import com.merza.Cinema.exception.ElementNotFoundException;
import com.merza.Cinema.repository.UserRepository;
import com.merza.Cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->new ElementNotFoundException(id));
    }

    @Override
    public AppUser save(AppUser appUser) {
        return userRepository.save(appUser);
    }

    @Override
    public Optional<AppUser>  findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
