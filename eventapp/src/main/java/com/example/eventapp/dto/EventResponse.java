package com.example.eventapp.dto;

import com.example.eventapp.entity.EventStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class EventResponse {

    private Long id;

    private String title;

    private LocalDate date;

    private LocalTime time;

    private String location;

    private String description;

    private String category;

    private EventStatus status;

    private String ownerName;
}


