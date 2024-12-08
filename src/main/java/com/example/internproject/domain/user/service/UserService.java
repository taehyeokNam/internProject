package com.example.internproject.domain.user.service;

import com.example.internproject.domain.user.dto.request.AuthSigninRequest;
import com.example.internproject.domain.user.dto.request.AuthSignupRequest;
import com.example.internproject.domain.user.dto.response.AuthSigninResponse;
import com.example.internproject.domain.user.dto.response.AuthSignupResponse;
import com.example.internproject.domain.user.entity.User;
import com.example.internproject.domain.user.repository.UserRepository;
import com.example.internproject.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Transactional
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

    public AuthSigninResponse signin(AuthSigninRequest request) {

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new NullPointerException("존재하지 않는 회원입니다"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("틀린 비밀번호입니다.");
        }

        // 토큰 생성
        String token = jwtUtil.createToken(
                user.getId(),
                user.getUsername(),
                user.getNickname(),
                user.getRole()
        );

        return new AuthSigninResponse(token);
    }
}
