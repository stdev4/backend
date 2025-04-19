package com.example.stdev_hack.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserStatsResponse {
    private int solvedQuizCount;
    private int correctRatio;
}
