package com.example.eventapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantResponse {

    private Long id;

    private String fullName;

    private String email;
}
