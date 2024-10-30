package com.v2.vetcare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specie;
    private String breed;
    private String sex;
    private String characteristics;
    private int age;
    private float weight;

    @ManyToOne
    private Client client;

    @OneToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    @JsonBackReference
    private ClinicHistory clinicHistory;
}
