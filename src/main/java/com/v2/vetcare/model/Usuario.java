package com.v2.vetcare.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String contrasenia;
    private String rol;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @JsonBackReference
    private Cliente cliente;

}
