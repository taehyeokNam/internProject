package com.example.internproject.domain.user.controller;

import com.example.internproject.domain.user.dto.AuthSignupRequest;
import com.example.internproject.domain.user.dto.AuthSignupResponse;
import com.example.internproject.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/auth/signup")
    public ResponseEntity<AuthSignupResponse> signup(@RequestBody AuthSignupRequest request) {
        return ResponseEntity.ok(userService.signup(request));
    }
}
