package com.example.stdev_hack.domain.Quiz;

import com.example.stdev_hack.dtos.CommonResponse;
import com.example.stdev_hack.dtos.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @RequestMapping("/daily")
    public ResponseEntity<CommonResponse<QuizResponse>> getDailyQuiz() {
        return ResponseEntity.ok(
                CommonResponse.<QuizResponse>builder()
                        .status(200)
                        .message("Success")
                        .data(quizService.findDailyQuiz())
                        .build()
        );
    }

}
