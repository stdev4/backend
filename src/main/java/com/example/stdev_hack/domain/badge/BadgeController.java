package com.example.stdev_hack.domain.badge;

import com.example.stdev_hack.dtos.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BadgeController {
    private final BadgeService badgeService;

    @PostMapping("/user/{userId}/badge/new")
    public ResponseEntity<CommonResponse<String>> grantNewBadgeForUser(@PathVariable Long userId, @RequestParam(name = "kind") String badgeName) {
        badgeService.saveUserBadge(userId, badgeName);
        return ResponseEntity.ok(
                CommonResponse.<String>builder()
                        .status(200)
                        .message("Success")
                        .data("뱃지 추가가 완료되었습니다.")
                        .build()
        );
    }
}
