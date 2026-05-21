package com.example.eventapp.repository;

import com.example.eventapp.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTitleContainingIgnoreCase(String keyword);
    List<Event> findByOwnerId(Long ownerId);
}
