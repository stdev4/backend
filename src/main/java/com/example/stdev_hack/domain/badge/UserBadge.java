package com.example.stdev_hack.domain.badge;

import com.example.stdev_hack.config.BaseEntity;
import com.example.stdev_hack.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user_badge")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public UserBadge(User owner, Badge badge) {
        this.owner = owner;
        this.badge = badge;
    }
}
