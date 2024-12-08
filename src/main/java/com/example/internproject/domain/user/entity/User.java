package com.example.internproject.domain.user.entity;

import com.example.internproject.domain.user.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nickname;

    @Builder
    public User(Long id, String username, String password, String nickname, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

}
