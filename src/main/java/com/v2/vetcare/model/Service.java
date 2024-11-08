package com.v2.vetcare.model;

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
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symptoms;
    private String requiredService;
    private double servicePrice;

    @OneToOne(mappedBy = "service")
    @JsonManagedReference(value = "serviceGroomingReference")
    private GroomingService groomingService;

    @OneToOne(mappedBy = "service")
    @JsonManagedReference(value = "serviceConsultationReference")
    private ConsultationService consultationService;

    @OneToOne(mappedBy = "service")
    @JsonManagedReference(value = "serviceEuthanasiaReference")
    private EuthanasiaService euthanasiaService;

    @OneToOne(mappedBy = "service")
    @JsonManagedReference(value = "servicePensionReference")
    private PensionService pensionService;

    @OneToOne(mappedBy = "service")
    @JsonManagedReference(value = "serviceSurgeryReference")
    private SurgeryService surgeryService;

    @OneToOne(mappedBy = "service")
    @JsonManagedReference(value = "serviceVaccineReference")
    private VaccineService vaccineService;


}
