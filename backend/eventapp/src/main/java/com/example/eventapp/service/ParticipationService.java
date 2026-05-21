package com.example.eventapp.service;

import com.example.eventapp.dto.ParticipantResponse;
import com.example.eventapp.entity.Event;
import com.example.eventapp.entity.EventStatus;
import com.example.eventapp.entity.Participation;
import com.example.eventapp.entity.User;
import com.example.eventapp.exception.BadRequestException;
import com.example.eventapp.exception.NotFoundException;
import com.example.eventapp.repository.EventRepository;
import com.example.eventapp.repository.ParticipationRepository;
import com.example.eventapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ParticipationService {

    private final ParticipationRepository
            participationRepository;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    //Constructor
    public ParticipationService(
            ParticipationRepository participationRepository,
            EventRepository eventRepository,
            UserRepository userRepository
    ) {

        this.participationRepository =
                participationRepository;

        this.eventRepository =
                eventRepository;

        this.userRepository =
                userRepository;
    }

    //Join Event
    public String join(
            Long eventId,
            Long userId
    ) {

        Event event = eventRepository.findById(
                eventId
        ).orElseThrow(() ->
                new NotFoundException(
                        "Etkinlik bulunamadı"
                )
        );

        User user = userRepository.findById(
                userId
        ).orElseThrow(() ->
                new NotFoundException(
                        "Kullanıcı bulunamadı"
                )
        );

        if (
                event.getStatus()
                        != EventStatus.PUBLISHED
        ) {

            throw new BadRequestException(
                    "Bu etkinlik yayında değil"
            );
        }

        if (
                event.getOwner()
                        .getId()
                        .equals(userId)
        ) {

            throw new BadRequestException(
                    "Kendi etkinliğinize katılamazsınız"
            );
        }

        boolean alreadyJoined =
                participationRepository
                        .existsByUserIdAndEventId(
                                userId,
                                eventId
                        );

        if (alreadyJoined) {

            throw new BadRequestException(
                    "Bu etkinliğe zaten katıldınız"
            );
        }

        Participation participation =
                new Participation();

        participation.setUser(user);

        participation.setEvent(event);

        participationRepository.save(
                participation
        );

        return "Etkinliğe katılım başarılı";
    }

    //Get Participants of an Event
    public List<ParticipantResponse> participants(
            Long eventId
    ) {

        return participationRepository
                .findByEventId(eventId)
                .stream()
                .map(participation -> {

                    ParticipantResponse dto =
                            new ParticipantResponse();

                    dto.setId(
                            participation
                                    .getUser()
                                    .getId()
                    );

                    dto.setFullName(
                            participation
                                    .getUser()
                                    .getFullName()
                    );

                    dto.setEmail(
                            participation
                                    .getUser()
                                    .getEmail()
                    );

                    return dto;
                })
                .collect(Collectors.toList());
    }
}