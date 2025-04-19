package com.example.stdev_hack.domain.Quiz;

import com.example.stdev_hack.config.BaseEntity;
import com.example.stdev_hack.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "solved_log")
public class SolvedLog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solved_log_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz solvedQuiz;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User solver;
}
