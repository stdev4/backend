package com.example.stdev_hack.daos;

import com.example.stdev_hack.domain.user.Field;
import com.example.stdev_hack.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserReq {
    private String nickname;
    private int age;
    private String username;
    private String password;
    private List<Field> interests;

    public User toEntity() {
        return new User(nickname, age, username, password, new HashSet<>(this.interests));
    }
}
