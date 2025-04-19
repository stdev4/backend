package com.example.stdev_hack.domain.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final UserBadgeRepository userBadgeRepository;

    private Badge findBadgeByName(String name) {
        return badgeRepository.findByName(name).orElseThrow(
                () -> new IllegalArgumentException("Badge not found")
        );
    }
}
