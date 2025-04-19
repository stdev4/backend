package com.example.stdev_hack.dtos;

import com.example.stdev_hack.domain.Field;
import com.example.stdev_hack.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class UserInfoResponse {
    private Long userId;
    private String nickname;
    private Field interest;

    public static UserInfoResponse of(User user) {
        return new UserInfoResponse(user.getId(), user.getNickname(), user.getInterest());
    }
}
