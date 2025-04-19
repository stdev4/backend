CREATE TABLE user_badge
(
    user_badge_id BIGINT AUTO_INCREMENT NOT NULL,
    user_id       BIGINT                NULL,
    badge_id      BIGINT                NULL,
    CONSTRAINT pk_userbadge PRIMARY KEY (user_badge_id)
);

ALTER TABLE user_badge
    ADD CONSTRAINT FK_USERBADGE_ON_BADGE FOREIGN KEY (badge_id) REFERENCES badge (badge_id);

ALTER TABLE user_badge
    ADD CONSTRAINT FK_USERBADGE_ON_USER FOREIGN KEY (user_id) REFERENCES user (user_id);