package com.v2.vetcare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GroomingService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requiredTreatment;
    private String observations;
    private String hairCondition;
    private double price;

    @OneToOne
    @JoinColumn(name = "vetService_id")
    @JsonBackReference(value = "serviceGroomingReference")
    private VetService vetService;
}
