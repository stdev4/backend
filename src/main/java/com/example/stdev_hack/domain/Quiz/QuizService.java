package com.example.stdev_hack.domain.Quiz;

import com.example.stdev_hack.daos.CustomQuizReq;
import com.example.stdev_hack.daos.QuizSolvingReq;
import com.example.stdev_hack.domain.user.SolvedLog;
import com.example.stdev_hack.domain.user.SolvedLogRepository;
import com.example.stdev_hack.domain.user.User;
import com.example.stdev_hack.domain.user.UserService;
import com.example.stdev_hack.dtos.CustomQuizResponse;
import com.example.stdev_hack.dtos.QuizExplanationResponse;
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
    private final SolvedLogRepository solvedLogRepository;

    public Quiz findQuizById(Long id) {
        return quizRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Quiz not found")
        );
    }

    public List<SolvedLog> findAllSolvedLogsByQuizId(Long quizId) {
        return solvedLogRepository.findAllBySolvedQuizId(quizId);
    }

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

    @Transactional
    public QuizExplanationResponse saveSolvedLog(QuizSolvingReq req) {
        User user = userService.findUserById(req.getUserId());
        Quiz quiz = findQuizById(req.getQuizId());
        SolvedLog solvedLog = new SolvedLog(quiz, user, quiz.isAnswer() == req.isAnswer());
        solvedLogRepository.save(solvedLog);
        return new QuizExplanationResponse(quiz.isAnswer(), quiz.getExplanationTitle(), quiz.getExplanationBody(), calculateCorrectRatio(findAllSolvedLogsByQuizId(quiz.getId())));
    }

    private int calculateCorrectRatio(List<SolvedLog> solvedLogs) {
        int correctCount = 0;
        for (SolvedLog log : solvedLogs) {
            if (log.isWasCorrect()) {
                correctCount++;
            }
        }
        return (correctCount / solvedLogs.size()) * 100;
    }
}
