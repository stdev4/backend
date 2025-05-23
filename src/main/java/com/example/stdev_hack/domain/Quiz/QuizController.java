package com.example.stdev_hack.domain.Quiz;

import com.example.stdev_hack.daos.CustomQuizReq;
import com.example.stdev_hack.daos.QuizSolvingReq;
import com.example.stdev_hack.dtos.CommonResponse;
import com.example.stdev_hack.dtos.QuizExplanationResponse;
import com.example.stdev_hack.dtos.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/daily")
    public ResponseEntity<CommonResponse<QuizResponse>> getDailyQuiz(@RequestParam Long userId) {
        return ResponseEntity.ok(
                CommonResponse.<QuizResponse>builder()
                        .status(200)
                        .message("Success")
                        .data(quizService.findDailyQuiz(userId))
                        .build()
        );
    }

    @GetMapping("/interesting")
    public ResponseEntity<CommonResponse<List<QuizResponse>>> getInterestingRandomQuizzes(@RequestParam Long userId) {
        return ResponseEntity.ok(
                CommonResponse.<List<QuizResponse>>builder()
                        .status(200)
                        .message("Success")
                        .data(quizService.findInterestingRandomQuizzes(userId))
                        .build()
        );
    }

    @GetMapping("/random")
    public ResponseEntity<CommonResponse<List<QuizResponse>>> getRandomQuizzes(@RequestParam Long userId) {
        return ResponseEntity.ok(
                CommonResponse.<List<QuizResponse>>builder()
                        .status(200)
                        .message("Success")
                        .data(quizService.findEntireRandomQuizzes(userId))
                        .build()
        );
    }

    @PostMapping("/custom/new")
    public ResponseEntity<CommonResponse<Long>> createCustomQuiz(@RequestBody CustomQuizReq req) {
        return ResponseEntity.ok(
                CommonResponse.<Long>builder()
                        .status(200)
                        .message("Success")
                        .data(quizService.saveCustomQuiz(req))
                        .build()
        );
    }

    @PutMapping("/custom/update")
    public ResponseEntity<CommonResponse<Long>> createCustomQuiz(@RequestParam Long quizId, @RequestBody CustomQuizReq req) {
        return ResponseEntity.ok(
                CommonResponse.<Long>builder()
                        .status(200)
                        .message("Success")
                        .data(quizService.updateCustomQuiz(quizId, req))
                        .build()
        );
    }

    @PostMapping("/submit")
    public ResponseEntity<CommonResponse<QuizExplanationResponse>> submitQuiz(@RequestBody QuizSolvingReq req) {
        return ResponseEntity.ok(
                CommonResponse.<QuizExplanationResponse>builder()
                        .status(200)
                        .message("Success")
                        .data(quizService.saveSolvedLog(req))
                        .build()
        );
    }
}
