package com.v2.vetcare.request;

import lombok.Data;

@Data
public class SolicitudActualizarCliente {
    private Long id;
    private String nombre;
    private String paterno;
    private String materno;
    private String telefono;
    private String correo;
    private String direccion;
}
