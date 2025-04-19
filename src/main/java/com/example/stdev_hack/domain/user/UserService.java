package com.example.stdev_hack.domain.user;

import com.example.stdev_hack.daos.UserReq;
import com.example.stdev_hack.domain.badge.UserBadge;
import com.example.stdev_hack.dtos.CustomQuizResponse;
import com.example.stdev_hack.dtos.QuizReviewResponse;
import com.example.stdev_hack.dtos.UserBadgeResponse;
import com.example.stdev_hack.dtos.UserStatsResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SolvedLogRepository solvedLogRepository;

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
    }

    public List<CustomQuizResponse> findAllQuizzesMadeByCurrentUser(Long userId) {
        User user = findUserById(userId);
        return user.getGenerateQuizzes().stream()
                .map(q -> new CustomQuizResponse(userId, q.getQuestion(), q.isAnswer(), q.getExplanationBody(), q.getField(), q.getCreatedAt()))
                .toList();
    }

    @Transactional
    public User saveUser(UserReq req) {
        return userRepository.save(req.toEntity());
    }

    public boolean isNicknameExist(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }

    public boolean isUsernameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Transactional
    public User updateUser(Long userId, UserReq req) {
        User user = findUserById(userId);
        if (req.getNickname() != null) {
            user.setNickname(req.getNickname());
        }
        if (req.getAge() != 0) {
            user.setAge(req.getAge());
        }
        if (req.getUsername() != null) {
            user.setUsername(req.getUsername());
        }
        if (req.getPassword() != null) {
            user.setPassword(req.getPassword());
        }
        if (req.getInterest() != null) {
            user.setInterest(req.getInterest());
        }
        return user;
    }

    public UserStatsResponse getUserStats(Long userId) {
        List<SolvedLog> solvedLogs = solvedLogRepository.findAllBySolverId(userId);

        int correctCount = 0;
        for (SolvedLog log : solvedLogs) {
            if (log.isWasCorrect()) {
                correctCount++;
            }
        }
        return new UserStatsResponse(solvedLogs.size(), solvedLogs.isEmpty() ? 100 : ((int)((double) correctCount / (double) solvedLogs.size()) * 100));
    }

    public List<UserBadgeResponse> findAllUserBadges(Long userId) {
        List<UserBadge> userBadges = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        ).getBadges();
        List<UserBadgeResponse> responses = new ArrayList<>();
        for (UserBadge userBadge : userBadges) {
            responses.add(new UserBadgeResponse(userBadge.getBadge().getName(), userBadge.getCreatedAt()));
        }
        return responses;
    }

    public List<QuizReviewResponse> findWrongQuizzes(Long userId) {
        List<SolvedLog> wrongLogs = solvedLogRepository.findWrongLogs(userId);
        List<QuizReviewResponse> responses = new ArrayList<>();
        for (SolvedLog log : wrongLogs) {
            responses.add(new QuizReviewResponse(log.getSolvedQuiz().getId(), log.getSolvedQuiz().getQuestion(), log.getSolvedQuiz().isAnswer(),
                    log.getSolvedQuiz().getExplanationBody(), log.getSolvedQuiz().getField(), log.getCreatedAt()
            ));
        }
        return responses;
    }
}
