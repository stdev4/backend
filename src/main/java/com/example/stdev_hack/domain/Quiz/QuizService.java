package com.example.stdev_hack.domain.Quiz;

import com.example.stdev_hack.daos.CustomQuizReq;
import com.example.stdev_hack.daos.QuizSolvingReq;
import com.example.stdev_hack.domain.user.SolvedLog;
import com.example.stdev_hack.domain.user.SolvedLogRepository;
import com.example.stdev_hack.domain.user.User;
import com.example.stdev_hack.domain.user.UserService;
import com.example.stdev_hack.dtos.QuizExplanationResponse;
import com.example.stdev_hack.dtos.QuizResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Long saveCustomQuiz(CustomQuizReq req) {
        Quiz quiz = new Quiz(QuizType.CUSTOM, req.getField(), req.getQuestion(), req.isAnswer(), req.getExplanationBody());
        User creator = userService.findUserById(req.getUserId());
        quiz.setQuestionCreator(creator);
        return quizRepository.save(quiz).getId();
    }

    @Transactional
    public Long updateCustomQuiz(Long quizId, CustomQuizReq req) {
        Quiz quiz = findQuizById(quizId);
        if (req.getField() != null) {
            quiz.setField(req.getField());
        }
        if (!req.getQuestion().isEmpty()) {
            quiz.setQuestion(req.getQuestion());
        }
        if (!req.getExplanationBody().isEmpty()) {
            quiz.setExplanationBody(req.getExplanationBody());
        }
        quiz.setAnswer(req.isAnswer());
        return quizId;
    }

    public QuizResponse findDailyQuiz(Long userId) {
        List<Quiz> quizzes = quizRepository.findRandomUnsolvedQuiz(userId);
        return new QuizResponse(quizzes.get(0).getId(), quizzes.get(0).getQuestion(), quizzes.get(0).getField(), quizzes.get(0).getCreatedAt());
    }

    public List<QuizResponse> findInterestingRandomQuizzes(Long userId) {
        List<Quiz> quizzes = quizRepository.findRandomUnsolvedQuizzesByUserInterest(userId, userService.findUserById(userId).getInterest());
        return quizzes.stream()
                .map(q -> new QuizResponse(q.getId(), q.getQuestion(), q.getField(), q.getCreatedAt()))
                .toList().subList(0, 3);
    }

    public List<QuizResponse> findEntireRandomQuizzes(Long userId) {
        List<Quiz> quizzes = quizRepository.findRandomUnsolvedQuizzes(userId);
        return quizzes.stream()
                .map(q -> new QuizResponse(q.getId(), q.getQuestion(), q.getField(), q.getCreatedAt()))
                .toList().subList(0, 3);
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
