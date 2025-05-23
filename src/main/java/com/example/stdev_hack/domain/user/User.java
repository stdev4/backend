package com.example.stdev_hack.domain.user;

import com.example.stdev_hack.config.BaseEntity;
import com.example.stdev_hack.domain.Field;
import com.example.stdev_hack.domain.Quiz.Quiz;
import com.example.stdev_hack.domain.badge.UserBadge;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<UserBadge> badges = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<Quiz> generateQuizzes = new ArrayList<>();

    @OneToMany(mappedBy = "solver", cascade = CascadeType.ALL)
    private List<SolvedLog> solvedLogs = new ArrayList<>();

    public User(String nickname, int age, String username, String password, Field interest) {
        this.nickname = nickname;
        this.age = age;
        this.username = username;
        this.password = password;
        this.interest = interest;
    }

    public void addSolvedLog(SolvedLog log) {
        this.solvedLogs.add(log);
    }
}
