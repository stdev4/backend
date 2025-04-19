package com.example.stdev_hack.daos;

import com.example.stdev_hack.domain.Field;
import lombok.Getter;

@Getter
public class CustomQuizReq {
    private Long userId;
    private Field field;
    private String question;
    private String explanationBody;
    private boolean answer;
}
