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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String paternal;
    private String maternal;
    private String phoneNumber;
    private String email;
    private String address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;

    @OneToOne(mappedBy = "client")
    @JsonManagedReference
    private User user;


    public Client(String name, String paternal, String maternal, String phoneNumber, String email, String address) {
        this.name = name;
        this.paternal = paternal;
        this.maternal = maternal;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
}
