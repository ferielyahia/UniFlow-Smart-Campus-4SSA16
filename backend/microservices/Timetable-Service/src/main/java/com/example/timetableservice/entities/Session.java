package com.example.timetableservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // References vers d'autres microservices : simples identifiants
    @NotNull
    private Long courseId;

    @NotNull
    private Long roomId;

    @NotBlank
    @Size(max = 64)
    @Column(length = 64)
    private String teacherId;

    @NotBlank
    @Size(max = 20)
    @Column(length = 20)
    private String sessionType;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "timetable_id")
    private Timetable timetable;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;
}
