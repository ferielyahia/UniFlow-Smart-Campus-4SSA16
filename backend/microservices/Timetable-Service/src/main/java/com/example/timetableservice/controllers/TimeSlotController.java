package com.example.timetableservice.controllers;

import com.example.timetableservice.entities.TimeSlot;
import com.example.timetableservice.services.TimeSlotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time-slots")
@RequiredArgsConstructor
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @GetMapping
    public List<TimeSlot> findAll() {
        return timeSlotService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TimeSlot create(@Valid @RequestBody TimeSlot timeSlot) {
        return timeSlotService.create(timeSlot);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        timeSlotService.delete(id);
    }
}
