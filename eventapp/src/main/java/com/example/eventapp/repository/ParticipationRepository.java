package com.example.eventapp.repository;

import com.example.eventapp.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findByEventId(Long eventId);
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
}

