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
public class VaccineService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "vaccineService")
    @JsonManagedReference(value = "vaccineServiceReference")
    private List<Vaccine> vaccines;

    @OneToOne
    @JoinColumn(name = "service_id")
    @JsonBackReference(value = "serviceVaccineReference")
    private Service service;
}
