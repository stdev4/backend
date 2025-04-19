package com.example.stdev_hack.domain.Quiz;

import com.example.stdev_hack.daos.CustomQuizReq;
import com.example.stdev_hack.domain.user.User;
import com.example.stdev_hack.domain.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
