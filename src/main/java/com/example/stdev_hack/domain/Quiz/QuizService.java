package com.example.stdev_hack.domain.Quiz;

import com.example.stdev_hack.daos.CustomQuizReq;
import com.example.stdev_hack.domain.user.User;
import com.example.stdev_hack.domain.user.UserService;
import com.example.stdev_hack.dtos.CustomQuizResponse;
import com.example.stdev_hack.dtos.QuizResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final UserService userService;
    private final QuizRepository quizRepository;

    @Transactional
    public CustomQuizResponse saveCustomQuiz(CustomQuizReq req) {
        Quiz quiz = new Quiz(QuizType.CUSTOM, req.getField(), req.getQuestion(), req.isAnswer(), req.getExplanationBody());
        User creator = userService.findUserById(req.getUserId());
        quiz.setQuestionCreator(creator);
        quizRepository.save(quiz);
        return new CustomQuizResponse(creator.getId(), quiz.getQuestion(), quiz.isAnswer(), quiz.getExplanationBody(), quiz.getField(), quiz.getCreatedAt());
    }

    public QuizResponse findDailyQuiz() {
        Quiz quiz = quizRepository.findByReleaseDate(LocalDate.now());
        if (quiz == null) {
            throw new IllegalArgumentException("No quiz available");
        }
        return new QuizResponse(quiz.getId(), quiz.getQuestion(), quiz.getField(), quiz.getCreatedAt());
    }
}
