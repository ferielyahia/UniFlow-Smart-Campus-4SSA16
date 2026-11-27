package com.example.timetableservice.controllers;

import com.example.timetableservice.entities.Session;
import com.example.timetableservice.services.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping
    public List<Session> findAll() {
        return sessionService.findAll();
    }

    @GetMapping("/{id}")
    public Session findById(@PathVariable("id") Long id) {
        return sessionService.findById(id);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<Session> findByTeacher(@PathVariable("teacherId") String teacherId) {
        return sessionService.findByTeacher(teacherId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@Valid @RequestBody Session session) {
        return sessionService.create(session);
    }

    @PutMapping("/{id}")
    public Session update(@PathVariable("id") Long id, @Valid @RequestBody Session session) {
        return sessionService.update(id, session);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        sessionService.delete(id);
    }
}
