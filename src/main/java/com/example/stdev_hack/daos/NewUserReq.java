package com.example.stdev_hack.daos;

import com.example.stdev_hack.domain.Field;
import com.example.stdev_hack.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserReq {
    private String nickname;
    private int age;
    private String username;
    private String password;
    private Field interest;

    public User toEntity() {
        return new User(nickname, age, username, password, interest);
    }
}
