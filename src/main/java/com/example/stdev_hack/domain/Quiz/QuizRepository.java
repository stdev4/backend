package com.example.stdev_hack.domain.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Quiz findByReleaseDate(LocalDate date);
}
