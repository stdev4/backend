package com.example.stdev_hack.domain.user;

import com.example.stdev_hack.daos.UserReq;
import com.example.stdev_hack.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/onboarding/nickname/{nickname}/exists")
    public ResponseEntity<CommonResponse<Boolean>> checkNicknameExistence(@PathVariable String nickname) {
        return ResponseEntity.ok(
                CommonResponse.<Boolean>builder()
                        .status(200)
                        .message("Success")
                        .data(!userService.isNicknameExist(nickname))
                        .build()
        );
    }

    @GetMapping("/onboarding/username/{username}/exists")
    public ResponseEntity<CommonResponse<Boolean>> checkUsernameExistence(@PathVariable String username) {
        return ResponseEntity.ok(
                CommonResponse.<Boolean>builder()
                        .status(200)
                        .message("Success")
                        .data(!userService.isUsernameExist(username))
                        .build()
        );
    }

    @PostMapping("/onboarding/save")
    public ResponseEntity<CommonResponse<UserInfoResponse>> join(@RequestBody UserReq req) {
        User user = userService.saveUser(req);
        return ResponseEntity.ok(
                CommonResponse.<UserInfoResponse>builder()
                        .status(200)
                        .message("Success")
                        .data(UserInfoResponse.of(user))
                        .build()
        );
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<CommonResponse<String>> updateUserInfo(@PathVariable Long userId, @RequestBody UserReq req) {
        User user = userService.updateUser(userId, req);
        return ResponseEntity.ok(
                CommonResponse.<String>builder()
                        .status(200)
                        .message("Success")
                        .data("회원 정보 수정이 완료되었습니다.")
                        .build()
        );
    }

    @GetMapping("/{userId}/custom")
    public ResponseEntity<CommonResponse<List<CustomQuizResponse>>> getUsersCustomQuizzes(@PathVariable Long userId) {
        return ResponseEntity.ok(
                CommonResponse.<List<CustomQuizResponse>>builder()
                        .status(200)
                        .message("Success")
                        .data(userService.findAllQuizzesMadeByCurrentUser(userId))
                        .build()
        );
    }

    @GetMapping("/{userId}/stats")
    public ResponseEntity<CommonResponse<UserStatsResponse>> getUserStats(@PathVariable Long userId) {
        return ResponseEntity.ok(
                CommonResponse.<UserStatsResponse>builder()
                        .status(200)
                        .message("Success")
                        .data(userService.getUserStats(userId))
                        .build()
        );
    }

    @GetMapping("/{userId}/badge")
    public ResponseEntity<CommonResponse<List<UserBadgeResponse>>> getUserBadges(@PathVariable Long userId) {
        return ResponseEntity.ok(
                CommonResponse.<List<UserBadgeResponse>>builder()
                        .status(200)
                        .message("Success")
                        .data(userService.findAllUserBadges(userId))
                        .build()
        );
    }

    @GetMapping("/{userId}/review")
    public ResponseEntity<CommonResponse<List<QuizReviewResponse>>> getUserReviewQuizzes(@PathVariable Long userId) {
        return ResponseEntity.ok(
                CommonResponse.<List<QuizReviewResponse>>builder()
                        .status(200)
                        .message("Success")
                        .data(userService.findWrongQuizzes(userId))
                        .build()
        );
    }
}
