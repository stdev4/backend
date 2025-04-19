package com.example.stdev_hack.daos;

import com.example.stdev_hack.domain.Field;
import lombok.Getter;

@Getter
public class CustomQuizReq {
    private Long userId;
    private String question;
    private boolean answer;
    private String explanationBody;
    private Field field;
}
