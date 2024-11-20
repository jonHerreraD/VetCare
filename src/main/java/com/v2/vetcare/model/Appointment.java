package com.v2.vetcare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime hour;
    private String status;


    @ManyToOne
    @JoinColumn(name = "pet_id")
    @JsonBackReference(value = "petAppointmentReference")
    private Pet pet;

    @OneToOne
    @JoinColumn(name = "vetService_id")
    @JsonBackReference(value = "serviceAppointmentReference")
    private VetService vetService;

    @ManyToOne
    @JoinColumn(name = "healthRecord_id")
    @JsonBackReference(value = "healthRecordAppointmentReference")
    private HealthRecord healthRecord;
}
