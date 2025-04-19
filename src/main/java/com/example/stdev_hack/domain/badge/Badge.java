package com.example.stdev_hack.domain.badge;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "badge")
public class Badge {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private Long id;

    @Column(name = "badge_name", nullable = false)
    private String name;

    @Column(name = "earning_condition", nullable = false)
    private String earningCondition;
}
