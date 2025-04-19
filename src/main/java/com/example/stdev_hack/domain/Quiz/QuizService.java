package com.example.stdev_hack.domain.Quiz;

import com.example.stdev_hack.daos.CustomQuizReq;
import com.example.stdev_hack.domain.user.User;
import com.example.stdev_hack.domain.user.UserService;
import com.example.stdev_hack.dtos.QuizResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final UserService userService;
    private final QuizRepository quizRepository;

    @Transactional
    public Quiz saveCustomQuiz(CustomQuizReq req) {
        Quiz quiz = new Quiz(QuizType.CUSTOM, req.getField(), req.getQuestion(), req.isAnswer(), req.getExplanationBody());
        User creator = userService.findUserById(req.getUserId());
        quiz.setQuestionCreator(creator);
        return quizRepository.save(quiz);
    }

    public QuizResponse findDailyQuiz() {
        Quiz quiz = quizRepository.findByReleaseDate(LocalDate.now());
        if (quiz == null) {
            throw new IllegalArgumentException("No quiz available");
        }
        return new QuizResponse(quiz.getId(), quiz.getQuestion(), quiz.getField(), quiz.getCreatedAt());
    }
}
