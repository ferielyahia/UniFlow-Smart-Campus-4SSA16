package com.example.timetableservice.repositories;

import com.example.timetableservice.entities.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
