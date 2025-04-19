package com.example.stdev_hack.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolvedLogRepository extends JpaRepository<SolvedLog, Long> {
    List<SolvedLog> findAllBySolverId(Long userId);
    List<SolvedLog> findAllBySolvedQuizId(Long solvedQuizId);
}
