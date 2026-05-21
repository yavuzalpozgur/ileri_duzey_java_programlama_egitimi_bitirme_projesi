package com.example.eventapp.controller;

import com.example.eventapp.config.SessionUtil;
import com.example.eventapp.dto.EventCreateRequest;
import com.example.eventapp.dto.EventResponse;
import com.example.eventapp.service.EventService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final SessionUtil sessionUtil;

    //Constructor
    public EventController(
            EventService eventService,
            SessionUtil sessionUtil
    ) {

        this.eventService = eventService;
        this.sessionUtil = sessionUtil;
    }

    //Make an endpoint
    @PostMapping
    public EventResponse create(
            @Valid @RequestBody
            EventCreateRequest request,

            HttpSession session
    ) {

        Long userId =
                sessionUtil.getUserId(session);

        return eventService.create(
                request,
                userId
        );
    }

    //Get All Events
    @GetMapping
    public List<EventResponse> getAll() {

        return eventService.getAll();
    }

    //Get Event By Id
    @GetMapping("/{id}")
    public EventResponse getById(
            @PathVariable Long id
    ) {

        return eventService.getById(id);
    }

    //Search Events
    @GetMapping("/search")
    public List<EventResponse> search(
            @RequestParam String keyword
    ) {

        return eventService.search(keyword);
    }

    //Get Events By Owner
    @GetMapping("/my")
    public List<EventResponse> myEvents(
            HttpSession session
    ) {

        Long userId =
                sessionUtil.getUserId(session);

        return eventService.myEvents(userId);
    }

    //Update Event
    @PutMapping("/{id}")
    public EventResponse update(
            @PathVariable Long id,

            @Valid @RequestBody
            EventCreateRequest request,

            HttpSession session
    ) {

        Long userId =
                sessionUtil.getUserId(session);

        return eventService.update(
                id,
                request,
                userId
        );
    }

    //Delete Event
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id,
            HttpSession session
    ) {

        Long userId =
                sessionUtil.getUserId(session);

        eventService.delete(id, userId);
    }

    //Publish Event
    @PostMapping("/{id}/publish")
    public EventResponse publish(
            @PathVariable Long id,
            HttpSession session
    ) {

        Long userId =
                sessionUtil.getUserId(session);

        return eventService.publish(id, userId);
    }

    //Stop Event
    @PostMapping("/{id}/stop")
    public EventResponse stop(
            @PathVariable Long id,
            HttpSession session
    ) {

        Long userId =
                sessionUtil.getUserId(session);

        return eventService.stop(id, userId);
    }

    //Archive Event
    @PostMapping("/{id}/archive")
    public EventResponse archive(
            @PathVariable Long id,
            HttpSession session
    ) {

        Long userId =
                sessionUtil.getUserId(session);

        return eventService.archive(id, userId);
    }

    //Pagination
    @GetMapping("/paged")
    public Page<EventResponse> paged(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size
    ) {

        return eventService.getPaged(
                page,
                size
        );
    }
}
