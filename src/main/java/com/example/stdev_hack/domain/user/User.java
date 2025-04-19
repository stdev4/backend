package com.example.stdev_hack.domain.user;

import com.example.stdev_hack.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private Set<Field> interests = new HashSet<>();

    public User(String nickname, int age, String username, String password, Set<Field> interests) {
        this.nickname = nickname;
        this.age = age;
        this.username = username;
        this.password = password;
        this.interests = interests;
    }
}
