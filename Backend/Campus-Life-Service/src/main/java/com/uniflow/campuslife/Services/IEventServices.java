package com.uniflow.campuslife.Services;

import com.uniflow.campuslife.Domain.Event;

import java.util.List;

public interface IEventServices {
    Event addEvent(Event event);
    Event getEvent(Long id);
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);
    List<Event> getAll();
}
