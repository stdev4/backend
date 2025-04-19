package com.example.stdev_hack.config;

import com.example.stdev_hack.dtos.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusCheckController {
    @GetMapping("/api/status")
    public ResponseEntity<CommonResponse<String>> statusCheck() {
        return ResponseEntity.ok(
                CommonResponse.<String>builder()
                        .status(200)
                        .message("Success")
                        .data("Server is running.")
                        .build()
        );
    }
}
