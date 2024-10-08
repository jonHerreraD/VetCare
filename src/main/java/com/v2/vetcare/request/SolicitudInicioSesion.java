package com.v2.vetcare.request;

import lombok.Data;

@Data
public class SolicitudInicioSesion {
    private String nombreUsuario;
    private String contrasenia;
}
