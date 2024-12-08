package com.example.internproject.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthSignupRequest {

    private String username;
    private String password;
    private String nickname;
}
