package com.example.internproject.domain.user.dto;

import com.example.internproject.domain.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AuthSignupResponse {

    private final String username;
    private final String nickname;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthSignupResponse(String username, String nickname, UserRole role) {
        this.username = username;
        this.nickname = nickname;
        this.authorities = List.of(new SimpleGrantedAuthority(role.getUserRole()));
    }
}
