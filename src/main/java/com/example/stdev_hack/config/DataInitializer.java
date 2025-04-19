package com.example.stdev_hack.config;

import com.example.stdev_hack.domain.Field;
import com.example.stdev_hack.domain.user.User;
import com.example.stdev_hack.domain.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (userRepository.findByNickname("<SYSTEM>").isEmpty()) {
            User systemUser = new User("<SYSTEM>", 0, "<SYSTEM>", "<SYSTEM>", Field.OTHERS);
            userRepository.save(systemUser);
        }
    }

}
