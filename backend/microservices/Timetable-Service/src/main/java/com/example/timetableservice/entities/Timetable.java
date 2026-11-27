package com.example.timetableservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(length = 100)
    private String label;

    @NotBlank
    @Size(max = 50)
    @Column(length = 50)
    private String groupName;

    @NotBlank
    @Size(max = 10)
    @Column(length = 10)
    private String semester;

    @NotBlank
    @Size(max = 20)
    @Column(length = 20)
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "timetable")
    private List<Session> sessions = new ArrayList<>();
}
