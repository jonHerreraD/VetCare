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
public class PensionService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accessories;
    private double dailyCost;
    private double totalPrice;

    @OneToOne
    @JoinColumn(name = "service_id")
    @JsonBackReference(value = "servicePensionReference")
    private Service service;
}
