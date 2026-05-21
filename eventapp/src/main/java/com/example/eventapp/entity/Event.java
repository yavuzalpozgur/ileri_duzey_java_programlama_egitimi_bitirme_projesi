package com.example.eventapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate date;

    private LocalTime time;

    private String location;

    @Column(length = 1000)
    private String description;

    private String category;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne
    private User owner;
}

