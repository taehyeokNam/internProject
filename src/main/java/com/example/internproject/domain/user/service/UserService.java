package com.example.internproject.domain.user.service;

import com.example.internproject.domain.user.dto.AuthSignupRequest;
import com.example.internproject.domain.user.dto.AuthSignupResponse;
import com.example.internproject.domain.user.entity.User;
import com.example.internproject.domain.user.enums.UserRole;
import com.example.internproject.domain.user.repository.UserRepository;
import com.example.internproject.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthSignupResponse signup(AuthSignupRequest request) {

        // 비밀번호 암호화
        String encodePassword = passwordEncoder.encode(request.getPassword());

        // 유저 객체 생성, 유저 DB 저장
        User user = userRepository.save(
                User.builder()
                        .username(request.getUsername())
                        .password(encodePassword)
                        .nickname(request.getNickname())
                        .build())
                ;

        // DTO 객체 생성 및 반환
        return new AuthSignupResponse(
                user.getUsername(),
                user.getNickname(),
                user.getRole()
        );
    }
}
