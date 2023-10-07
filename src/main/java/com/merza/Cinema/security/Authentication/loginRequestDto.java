package com.merza.Cinema.security.Authentication;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class loginRequestDto {

    private String username;
    private String password;
}
