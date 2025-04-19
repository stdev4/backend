package com.example.stdev_hack.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuizExplanationResponse {
    private boolean answer;
    private String explanationTitle;
    private String explanationBody;
    private int correctRate;
}
