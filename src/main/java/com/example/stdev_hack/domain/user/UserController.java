package com.example.stdev_hack.domain.user;

import com.example.stdev_hack.dtos.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
