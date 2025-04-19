package com.example.stdev_hack.domain.user;

import com.example.stdev_hack.config.BaseEntity;
import com.example.stdev_hack.domain.Field;
import jakarta.persistence.*;
import lombok.*;

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
    @Setter
    private String nickname;

    @Column(nullable = false)
    @Setter
    private int age;

    @Column(nullable = false, unique = true)
    @Setter
    private String username;

    @Column(nullable = false)
    @Setter
    private String password;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    @Setter
    private Field interest;

    public User(String nickname, int age, String username, String password, Field interest) {
        this.nickname = nickname;
        this.age = age;
        this.username = username;
        this.password = password;
        this.interest = interest;
    }
}
