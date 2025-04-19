package com.example.stdev_hack.daos;

import lombok.Getter;

@Getter
public class QuizSolvingReq {
    private Long userId;
    private Long quizId;
    private boolean answer;
}
