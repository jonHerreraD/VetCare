package com.v2.vetcare.request;

import com.v2.vetcare.model.Usuario;
import lombok.Data;

@Data
public class SolicitudCrearUsuario {
    private Usuario usuario;
    private Long idCliente;
}
