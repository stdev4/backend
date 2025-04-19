package com.example.stdev_hack.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserBadgeResponse {
    private String badgeName;
    private LocalDateTime earnedAt;
}
