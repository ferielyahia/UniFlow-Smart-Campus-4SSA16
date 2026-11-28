package com.uniflow.campuslife.Web.Controllers;

import com.uniflow.campuslife.Domain.Event;
import com.uniflow.campuslife.Services.IEventServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("events")
@Tag(name = "Events", description = "Gestion des événements du campus")
public class EventController {
    private final IEventServices iEventServices;

    @Operation(summary = "Add : Event")
    @ApiResponse(responseCode = "200", description = "Event Added")
    @PostMapping("add")
    Event createEvent(@RequestBody Event event) {
        return iEventServices.addEvent(event);
    }

    @Operation(summary = "Show : Event")
    @ApiResponse(responseCode = "200", description = "Event Listed")
    @GetMapping("{id}")
    Event afficherEvent(@PathVariable Long id) {
        return iEventServices.getEvent(id);
    }

    @Operation(summary = "Update : Event")
    @ApiResponse(responseCode = "200", description = "Event Updated")
    @PutMapping("update/{id}")
    Event modifierEvent(@PathVariable Long id, @RequestBody Event event) {
        return iEventServices.updateEvent(id, event);
    }

    @Operation(summary = "Delete : Event")
    @ApiResponse(responseCode = "200", description = "Event Deleted")
    @DeleteMapping("{id}")
    void supprimerEvent(@PathVariable Long id) {
        iEventServices.deleteEvent(id);
    }

    @Operation(summary = "Show All : Event")
    @ApiResponse(responseCode = "200", description = "List of Events")
    @GetMapping("getAll")
    List<Event> afficherEvents() {
        return iEventServices.getAll();
    }

}
