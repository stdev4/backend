package com.example.stdev_hack.domain.Quiz;

import com.example.stdev_hack.domain.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("SELECT q FROM Quiz q WHERE q.releaseDate = :date")
    Quiz findByReleaseDate(@Param("date") LocalDate date);

    @Query("""
    SELECT q
    FROM Quiz q
    WHERE q.type = com.example.stdev_hack.domain.Quiz.QuizType.RANDOM
      AND q.field = :interest
      AND q.id NOT IN (
          SELECT sl.solvedQuiz.id
          FROM SolvedLog sl
          WHERE sl.solver.id = :userId
      )
    ORDER BY function('RAND')
    """)
    List<Quiz> findRandomUnsolvedQuizzesByUserInterest(
            @Param("userId") Long userId,
            @Param("interest") Field interest
    );

    @Query("""
    SELECT q
    FROM Quiz q
    WHERE q.type = com.example.stdev_hack.domain.Quiz.QuizType.RANDOM
      AND q.id NOT IN (
          SELECT sl.solvedQuiz.id
          FROM SolvedLog sl
          WHERE sl.solver.id = :userId
      )
    ORDER BY function('RAND')
    """)
    List<Quiz> findRandomUnsolvedQuizzes(@Param("userId") Long userId);
}
