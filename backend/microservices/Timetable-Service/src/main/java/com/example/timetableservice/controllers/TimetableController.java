package com.example.timetableservice.controllers;

import com.example.timetableservice.entities.Timetable;
import com.example.timetableservice.services.TimetableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetables")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @GetMapping
    public List<Timetable> findAll() {
        return timetableService.findAll();
    }

    @GetMapping("/{id}")
    public Timetable findById(@PathVariable("id") Long id) {
        return timetableService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Timetable create(@Valid @RequestBody Timetable timetable) {
        return timetableService.create(timetable);
    }

    @PutMapping("/{id}")
    public Timetable update(@PathVariable("id") Long id, @Valid @RequestBody Timetable timetable) {
        return timetableService.update(id, timetable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        timetableService.delete(id);
    }
}
