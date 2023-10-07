package com.merza.Cinema.security.Authentication;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDto {

    private String token;
}
