package com.example.stdev_hack.domain.user;

import com.example.stdev_hack.config.BaseEntity;
import com.example.stdev_hack.domain.Field;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "user")
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

    @OneToMany
    private Set<Field> interests = new HashSet<>();
}
