package com.example.stdev_hack.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Field {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private Long id;

    @Column(name = "field_name", nullable = false)
    private String name;
}
