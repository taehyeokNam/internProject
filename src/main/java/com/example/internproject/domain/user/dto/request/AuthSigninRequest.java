package com.example.internproject.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthSigninRequest {

    private String username;
    private String password;
}
