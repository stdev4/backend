package com.example.stdev_hack.domain.badge;

import com.example.stdev_hack.config.BaseEntity;
import com.example.stdev_hack.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class UserBadge extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_badge_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "badge_id")
    private Badge badge;
}
