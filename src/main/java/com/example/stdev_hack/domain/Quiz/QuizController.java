package com.example.stdev_hack.domain.Quiz;

import com.example.stdev_hack.daos.CustomQuizReq;
import com.example.stdev_hack.dtos.CommonResponse;
import com.example.stdev_hack.dtos.CustomQuizResponse;
import com.example.stdev_hack.dtos.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/daily")
    public ResponseEntity<CommonResponse<QuizResponse>> getDailyQuiz() {
        return ResponseEntity.ok(
                CommonResponse.<QuizResponse>builder()
                        .status(200)
                        .message("Success")
                        .data(quizService.findDailyQuiz())
                        .build()
        );
    }

    @PostMapping("/custom/new")
    public ResponseEntity<CommonResponse<CustomQuizResponse>> createCustomQuiz(@RequestBody CustomQuizReq req) {
        return ResponseEntity.ok(
                CommonResponse.<CustomQuizResponse>builder()
                        .status(200)
                        .message("Success")
                        .data(quizService.saveCustomQuiz(req))
                        .build()
        );
    }
}
