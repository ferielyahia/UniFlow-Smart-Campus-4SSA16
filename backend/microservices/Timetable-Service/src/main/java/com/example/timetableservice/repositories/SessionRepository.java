package com.example.timetableservice.repositories;

import com.example.timetableservice.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByTeacherId(String teacherId);

    List<Session> findByTimeSlotId(Long timeSlotId);

    boolean existsByTimetableId(Long timetableId);

    boolean existsByTimeSlotId(Long timeSlotId);
}
