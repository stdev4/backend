package com.example.stdev_hack.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolvedLogRepository extends JpaRepository<SolvedLog, Long> {
    List<SolvedLog> findAllBySolverId(Long userId);
    List<SolvedLog> findAllBySolvedQuizId(Long solvedQuizId);

    @Query("select sl from SolvedLog sl where sl.solver.id = :userId and sl.wasCorrect = :false")
    List<SolvedLog> findWrongLogs(Long userId);
}
