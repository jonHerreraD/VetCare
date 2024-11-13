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
public class ClinicHistory {
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
    @JsonBackReference(value = "petClinicHistoryReference")
    private Pet pet;

    @OneToMany(mappedBy = "clinicHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;
}
