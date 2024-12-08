package com.example.internproject.domain.user.dto;

import lombok.Getter;

@Getter
public class AuthSignupRequest {

    private String username;
    private String password;
    private String nickname;
}
