package com.example.stdev_hack.domain.badge;

import com.example.stdev_hack.domain.user.User;
import com.example.stdev_hack.domain.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final UserBadgeRepository userBadgeRepository;
    private final UserService userService;

    private Badge findBadgeByName(String name) {
        return badgeRepository.findByName(name).orElseThrow(
                () -> new IllegalArgumentException("Badge not found")
        );
    }

    @Transactional
    public UserBadge saveUserBadge(Long userId, String badgeName) {
        User user = userService.findUserById(userId);
        Badge badge = findBadgeByName(badgeName);
        UserBadge userBadge = new UserBadge(user, badge);
        return userBadgeRepository.save(userBadge);
    }
}
