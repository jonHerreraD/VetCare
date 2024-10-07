package com.v2.vetcare.service.usuario;

import com.v2.vetcare.model.Cliente;
import com.v2.vetcare.model.Usuario;
import com.v2.vetcare.request.SolicitudActualizarCliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServicioUsuario {

    Usuario crearUsuario(Usuario usuario, Cliente cliente);
    Usuario obtenerUsuarioPorId(Long id);
    void eliminarUsuario(Long id);
    Usuario actualizarUsuario(Usuario usuario);

    List<Usuario> obtenerTodosLosUsuarios();
}
