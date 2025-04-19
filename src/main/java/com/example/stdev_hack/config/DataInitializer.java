package com.example.stdev_hack.config;

import com.example.stdev_hack.domain.Field;
import com.example.stdev_hack.domain.badge.Badge;
import com.example.stdev_hack.domain.badge.BadgeRepository;
import com.example.stdev_hack.domain.user.User;
import com.example.stdev_hack.domain.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        initSystemUser();
        initBadges();
    }

    private void initBadges() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/badges.json");

        if (inputStream == null) {
            throw new FileNotFoundException("badges.json not found in resources.");
        }

        Badge[] badgeList = objectMapper.readValue(inputStream, Badge[].class);

        for (Badge badge : badgeList) {
            badgeRepository.findByName(badge.getName())
                    .orElseGet(() -> badgeRepository.save(badge));
        }
    }

    private void initSystemUser() {
        if (userRepository.findByNickname("<SYSTEM>").isEmpty()) {
            User systemUser = new User("<SYSTEM>", 0, "<SYSTEM>", "<SYSTEM>", Field.OTHERS);
            userRepository.save(systemUser);
        }
    }
}
