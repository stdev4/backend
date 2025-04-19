package com.example.stdev_hack.dtos;

import com.example.stdev_hack.domain.Field;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class QuizResponse {
    private Long quizId;
    private String question;
    private Field field;
    private LocalDateTime createdAt;
}
