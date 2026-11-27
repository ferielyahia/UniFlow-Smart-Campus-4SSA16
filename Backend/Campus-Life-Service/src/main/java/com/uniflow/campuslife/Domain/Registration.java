package com.uniflow.campuslife.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"event_id", "studentId"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long studentId;
    LocalDateTime registrationDate;
    @Enumerated(value = EnumType.STRING)
    RegistrationStatus status;

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;
}
