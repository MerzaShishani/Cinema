package com.merza.Cinema.security;

import com.merza.Cinema.entity.AppUser;
import com.merza.Cinema.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Setter
@Getter
@NoArgsConstructor
public class AuthUserDetails implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private Set<Role> roles = new HashSet<>();

    private boolean isEnabled = true;

    private boolean isCredentialsNonExpired = true;

    private boolean isAccountNonLocked = true;

    private boolean isAccountNonExpired = true;


    public AuthUserDetails(AppUser appUser) {
        this.id = appUser.getId();
        this.username = appUser.getUsername();
        this.password = appUser.getPassword();
        this.roles = appUser.getRoles();
        this.isEnabled = appUser.isEnabled();
        this.isCredentialsNonExpired = appUser.isCredentialsNonExpired();
        this.isAccountNonLocked = appUser.isAccountNonLocked();
        this.isAccountNonExpired = appUser.isAccountNonExpired();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
}
