package com.uniflow.campuslife.Domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    String location;
    @Enumerated(value = EnumType.STRING)
    Category category;
    LocalDate startDate;
    LocalDate endDate;
    Integer capacity;
    @Enumerated(value = EnumType.STRING)
    EventStatus status;
    String organizerId;
    Long roomId;

    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    Set<Registration> registrations;
}
