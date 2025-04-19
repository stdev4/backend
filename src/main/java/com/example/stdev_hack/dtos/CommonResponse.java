package com.example.stdev_hack.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse<T> {
    private int status;
    private String message;
    private T data;
}
