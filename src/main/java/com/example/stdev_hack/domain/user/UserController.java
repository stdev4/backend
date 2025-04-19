package com.example.stdev_hack.domain.user;

import com.example.stdev_hack.dtos.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                        .data(userService.isNicknameExist(nickname))
                        .build()
        );
    }
}
