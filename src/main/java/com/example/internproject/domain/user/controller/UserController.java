package com.example.internproject.domain.user.controller;

import com.example.internproject.domain.user.dto.request.AuthSigninRequest;
import com.example.internproject.domain.user.dto.request.AuthSignupRequest;
import com.example.internproject.domain.user.dto.response.AuthSigninResponse;
import com.example.internproject.domain.user.dto.response.AuthSignupResponse;
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

    // 로그인
    @PostMapping("/auth/signin")
    public ResponseEntity<AuthSigninResponse> signin(@RequestBody AuthSigninRequest request) {
        return ResponseEntity.ok(userService.signin(request));
    }

}
