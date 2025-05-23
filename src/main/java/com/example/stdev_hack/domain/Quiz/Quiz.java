package com.example.stdev_hack.domain.Quiz;

import com.example.stdev_hack.config.BaseEntity;
import com.example.stdev_hack.domain.Field;
import com.example.stdev_hack.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "quiz")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long id;

    @Column(name = "quiz_type", nullable = false, columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private QuizType type;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "field", nullable = false, columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    @Setter
    private Field field;

    @Column(name = "question", nullable = false)
    @Setter
    private String question;

    @Column(name = "answer", nullable = false)
    @Setter
    private boolean answer;

    @Column(name = "explanation_title")
    private String explanationTitle;

    @Column(name = "explanation_body", nullable = false, columnDefinition = "text")
    @Setter
    private String explanationBody;

    @Column(name = "explanation_source")
    private String explanationSource;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    public Quiz(QuizType type, Field field, String question, boolean answer, String explanationBody) {
        this.type = type;
        this.field = field;
        this.question = question;
        this.answer = answer;
        this.explanationBody = explanationBody;
    }

    public void setQuestionCreator(User creator) {
        this.creator = creator;
        this.creator.getGenerateQuizzes().add(this);
    }
}
