package com.example.eventapp.service;

import com.example.eventapp.dto.EventCreateRequest;
import com.example.eventapp.dto.EventResponse;
import com.example.eventapp.entity.Event;
import com.example.eventapp.entity.EventStatus;
import com.example.eventapp.entity.User;
import com.example.eventapp.exception.NotFoundException;
import com.example.eventapp.exception.UnauthorizedException;
import com.example.eventapp.repository.EventRepository;
import com.example.eventapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    //Constructor
    public EventService(
            EventRepository eventRepository,
            UserRepository userRepository
    ) {

        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    //DTO Mapper
    private EventResponse mapToResponse(
            Event event
    ) {

        EventResponse response =
                new EventResponse();

        response.setId(event.getId());

        response.setTitle(event.getTitle());

        response.setDate(event.getDate());

        response.setTime(event.getTime());

        response.setLocation(event.getLocation());

        response.setDescription(
                event.getDescription()
        );

        response.setCategory(event.getCategory());

        response.setStatus(event.getStatus());

        response.setOwnerName(
                event.getOwner().getFullName()
        );

        return response;
    }

    //Ownership Check
    private void checkOwnership(
            Event event,
            Long userId
    ) {

        if (!event.getOwner()
                .getId()
                .equals(userId)) {

            throw new UnauthorizedException(
                    "Bu işlem için yetkiniz yok"
            );
        }
    }

    //Make a new event
    public EventResponse create(
            EventCreateRequest request,
            Long userId
    ) {

        User owner = userRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Kullanıcı bulunamadı"
                        )
                );

        Event event = new Event();

        event.setTitle(request.getTitle());

        event.setDate(request.getDate());

        event.setTime(request.getTime());

        event.setLocation(request.getLocation());

        event.setDescription(
                request.getDescription()
        );

        event.setCategory(request.getCategory());

        event.setStatus(EventStatus.STOPPED);

        event.setOwner(owner);

        Event saved =
                eventRepository.save(event);

        return mapToResponse(saved);
    }

    //Get all events
    public List<EventResponse> getAll() {

        return eventRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //Get event by id
    public EventResponse getById(
            Long id
    ) {

        Event event = eventRepository.findById(id).orElseThrow(() ->
                        new NotFoundException("Etkinlik bulunamadı")
        );

        return mapToResponse(event);
    }

    //Get events by keyword
    public List<EventResponse> search(
            String keyword
    ) {

        return eventRepository
                .findByTitleContainingIgnoreCase(
                        keyword
                )
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //Get events by owner
    public List<EventResponse> myEvents(
            Long userId
    ) {

        return eventRepository
                .findByOwnerId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //Update Event
    public EventResponse update(
            Long eventId,
            EventCreateRequest request,
            Long userId
    ) {

        Event event = eventRepository.findById(
                eventId
        ).orElseThrow(() ->
                new NotFoundException(
                        "Etkinlik bulunamadı"
                )
        );

        checkOwnership(event, userId);

        event.setTitle(request.getTitle());

        event.setDate(request.getDate());

        event.setTime(request.getTime());

        event.setLocation(request.getLocation());

        event.setDescription(
                request.getDescription()
        );

        event.setCategory(request.getCategory());

        Event updated =
                eventRepository.save(event);

        return mapToResponse(updated);
    }

    //Delete Event
    public void delete(
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

        checkOwnership(event, userId);

        eventRepository.delete(event);
    }

    //Publish Event
    public EventResponse publish(
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

        checkOwnership(event, userId);

        event.setStatus(EventStatus.PUBLISHED);

        return mapToResponse(
                eventRepository.save(event)
        );
    }
    //Stop Event
    public EventResponse stop(
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

        checkOwnership(event, userId);

        event.setStatus(EventStatus.STOPPED);

        return mapToResponse(
                eventRepository.save(event)
        );
    }

    //Archieve Event
    public EventResponse archive(
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

        checkOwnership(event, userId);

        event.setStatus(EventStatus.ARCHIVED);

        return mapToResponse(
                eventRepository.save(event)
        );
    }

    //Pagination
    public Page<EventResponse> getPaged(
            int page,
            int size
    ) {

        return eventRepository
                .findAll(PageRequest.of(page, size))
                .map(this::mapToResponse);
    }
}
