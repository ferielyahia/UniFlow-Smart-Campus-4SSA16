package com.example.timetableservice.repositories;

import com.example.timetableservice.entities.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
}
