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
@Entity
@AllArgsConstructor
public class VetUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;

    @OneToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference(value = "clientReference")  // Cambiado el nombre a "clientReference"
    private Client client;

    @OneToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference(value = "employeeReference")  // Cambiado el nombre a "employeeReference"
    private Employee employee;

}
