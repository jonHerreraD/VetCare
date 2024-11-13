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
    @JsonBackReference(value = "clientPetReference")
    private Client client;

    /*
    @OneToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    @JsonBackReference
     */


    @OneToOne(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "petClinicHistoryReference")
    private ClinicHistory clinicHistory;


    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;


    public Pet(String name, String specie, String breed, String sex,
               String characteristics, int age, float weight, Client client) {
        this.name = name;
        this.specie = specie;
        this.breed = breed;
        this.sex = sex;
        this.characteristics = characteristics;
        this.age = age;
        this.weight = weight;
        this.client = client;
    }
}
