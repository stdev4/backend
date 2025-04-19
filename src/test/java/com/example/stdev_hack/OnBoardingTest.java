package com.example.stdev_hack;

import com.example.stdev_hack.domain.user.User;
import com.example.stdev_hack.domain.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringApplication.class)
public class OnBoardingTest {
    @Autowired
    private UserService userService;

    @Test
    void 회원_정보_저장_및_조회() {
        NewUserReq req = new NewUserReq("닉네임", 10, "test123", "password123", List.of("physics", "others"));
        User user = userService.saveUser(req);
        assertThat(user).isInstanceOf(User.class);
        assertThat(user.getNickname()).isEqualTo("nickname");
        assertThat(user.getAge()).isEqualTo(10);
        assertThat(user.getUsername()).isEqualTo("test123");
        assertThat(user.getPassword()).isEqualTo("password123");
        assertThat(user.getInterests()).hasSize(2);
        assertThat(user.getInterests()).extracting("name").containsExactlyInAnyOrder("physics", "others");
    }

    @Test
    void 아이디_중복_검사() {
        NewUserReq req = new NewUserReq("nickname2", 10, "test2", "password123", List.of("physics", "others"));
        User user = userService.saveUser(req);
        assertThat(userService.checkNicknameUniqueness("test2")).isFalse();
        assertThat(userService.checkNicknameUniqueness("test")).isTrue();
        assertThat(userService.checkNicknameUniqueness("uniqueNickname")).isTrue();
    }

    @Test
    void 닉네임_중복_검사() {
        NewUserReq req = new NewUserReq("nickname3", 10, "test3", "password123", List.of("physics", "others"));
        User user = userService.saveUser(req);
        assertThat(userService.checkUsernameUniqueness("test3")).isFalse();
        assertThat(userService.checkUsernameUniqueness("test")).isTrue();
        assertThat(userService.checkUsernameUniqueness("uniqueUsername")).isTrue();
    }
}
