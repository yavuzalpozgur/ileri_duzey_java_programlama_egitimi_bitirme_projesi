package com.example.eventapp.controller;

import com.example.eventapp.config.SessionUtil;
import com.example.eventapp.dto.ParticipantResponse;
import com.example.eventapp.service.ParticipationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participations")
public class ParticipationController {
    private final ParticipationService
            participationService;

    private final SessionUtil sessionUtil;

    //Constructor
    public ParticipationController(
            ParticipationService participationService,
            SessionUtil sessionUtil
    ) {

        this.participationService =
                participationService;

        this.sessionUtil = sessionUtil;
    }

    //Join Endpoint
    @PostMapping("/{eventId}/join")
    public String join(
            @PathVariable Long eventId,
            HttpSession session
    ) {

        Long userId =
                sessionUtil.getUserId(session);

        return participationService.join(
                eventId,
                userId
        );
    }

    //Participants Endpoint
    @GetMapping("/{eventId}/participants")
    public List<ParticipantResponse> participants(
            @PathVariable Long eventId
    ) {

        return participationService
                .participants(eventId);
    }

}
