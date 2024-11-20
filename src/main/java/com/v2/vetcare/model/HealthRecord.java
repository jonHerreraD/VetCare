package com.v2.vetcare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HealthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String notes;

    /*
    @OneToOne(mappedBy = "clinicHistory")
    @JsonManagedReference

     */

    @OneToOne
    @JoinColumn(name = "pet_id")
    @JsonBackReference(value = "petHealthRecordReference")
    private Pet pet;

    @OneToMany(mappedBy = "healthRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "healthRecordAppointmentReference")
    private List<Appointment> appointments;
}
