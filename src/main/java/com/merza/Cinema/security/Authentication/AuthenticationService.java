package com.merza.Cinema.security.Authentication;

import com.merza.Cinema.service.Impls.UserServiceImpl;
import com.merza.Cinema.entity.AppUser;
import com.merza.Cinema.repository.UserRepository;
import com.merza.Cinema.security.AuthUserDetails;
import com.merza.Cinema.security.JwtService;
import com.merza.Cinema.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationResponseDto register(RegisterRequestDto request) {
        var user = new AppUser(null,
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                Set.of(roleService.findByName("USER")),
                new ArrayList<>(),
                true, true,true,true

        );
        userService.save(user);
        var jwtToken = jwtService.generateToken(new AuthUserDetails(user));
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDto login(loginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        AppUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(new AuthUserDetails(user));
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}
