package com.v2.vetcare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class ConsultationService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double temperature;
    private int pulse;
    private String tllc;
    private String hydrationStatus;
    private double heartRate;
    private double respiratoryRate;
    private String reproductiveStatus;
    private boolean isWithOtherAnimals;
    private String currentIllness;
    private double price;

    @OneToOne
    @JoinColumn(name = "service_id")
    @JsonBackReference(value = "serviceConsultationReference")
    private Service service;

}
