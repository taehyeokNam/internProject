package com.example.internproject.domain.user.entity;

import com.example.internproject.domain.user.enums.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthUser {

    //private final Long userId;
    private final String username;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthUser(String username, String nickname, UserRole role) {
        //this.userId = userId;
        this.username = username;
        this.email = nickname;
        this.authorities = List.of(new SimpleGrantedAuthority(role.getUserRole()));
    }
}
