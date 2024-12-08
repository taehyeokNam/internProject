package com.example.internproject.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    USER(authorityName.USER),
    MASTER(authorityName.MASTER);

    private final String userRole;

    public static UserRole of(String role) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력값입니다."));
    }

    public static class authorityName {
        public static final String USER = "ROLE_USER";
        public static final String MASTER = "ROLE_MASTER";
    }
}