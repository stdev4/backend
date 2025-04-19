CREATE TABLE solved_log
(
    solved_log_id BIGINT AUTO_INCREMENT NOT NULL,
    quiz_id       BIGINT                NULL,
    user_id       BIGINT                NULL,
    CONSTRAINT pk_solvedlog PRIMARY KEY (solved_log_id)
);

ALTER TABLE solved_log
    ADD CONSTRAINT FK_SOLVEDLOG_ON_QUIZ FOREIGN KEY (quiz_id) REFERENCES quiz (quiz_id);

ALTER TABLE solved_log
    ADD CONSTRAINT FK_SOLVEDLOG_ON_USER FOREIGN KEY (user_id) REFERENCES user (user_id);