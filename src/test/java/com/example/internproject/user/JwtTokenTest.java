package com.example.internproject.user;

import com.example.internproject.domain.user.enums.UserRole;
import com.example.internproject.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class JwtTokenTest {

    private String secretKey = "eedc7fc6622248939c14cd2573beb25aeedc7fc6622248939c14cd2573beb25a";

    private JwtUtil jwtUtil;


    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        jwtUtil.setSecretKey(secretKey);
        jwtUtil.init(); // 키 초기화
    }

    @Test
    void createToken_Success() {
        // given
        Long userId = 1L;
        String username = "testUser";
        String nickname = "testNick";
        UserRole userRole = UserRole.USER;

        // when
        String token = jwtUtil.createToken(userId, username, nickname, userRole);

        // then
        assertThat(token).startsWith("Bearer ");
        assertThat(token.length()).isGreaterThan(0);
    }

    @Test
    void substringToken_Success() {
        // given
        String token = "Bearer testToken";

        // when
        String extractedToken = jwtUtil.substringToken(token);

        // then
        assertThat(extractedToken).isEqualTo("testToken");
    }

    @Test
    void substringToken_Fail() {
        // given
        String invalidToken = "InvalidToken";

        // when & then
        assertThatThrownBy(() -> jwtUtil.substringToken(invalidToken))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Not Found Token");
    }

    @Test
    void extractClaims_Success() {
        // given
        Long userId = 1L;
        String username = "testUser";
        String nickname = "testNick";
        UserRole userRole = UserRole.USER;

        String token = jwtUtil.createToken(userId, username, nickname, userRole);
        String rawToken = jwtUtil.substringToken(token); // "Bearer " 제거

        // when
        Claims claims = jwtUtil.extractClaims(rawToken);

        // then
        assertThat(claims.getSubject()).isEqualTo(String.valueOf(userId));
        assertThat(claims.get("username")).isEqualTo(username);
        assertThat(claims.get("nickname")).isEqualTo(nickname);
        assertThat(claims.get("userRole")).isEqualTo(userRole.name());
    }

    @Test
    void extractClaims_Fail_InvalidToken() {
        // given
        String invalidToken = "InvalidToken";

        // when & then
        assertThatThrownBy(() -> jwtUtil.extractClaims(invalidToken))
                .isInstanceOf(io.jsonwebtoken.JwtException.class);
    }
}
