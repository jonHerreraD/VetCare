package com.v2.vetcare.service.cliente;

import com.v2.vetcare.model.Cliente;
import com.v2.vetcare.request.SolicitudActualizarCliente;
import com.v2.vetcare.request.SolicitudAgregarCliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServicioCliente {
    Cliente registrarCliente(SolicitudAgregarCliente solicitudCliente);
    Cliente obtenerClientePorId(Long id);
    void eliminarCliente(Long id);
    Cliente actualizarCliente(SolicitudActualizarCliente solicitud);

    List<Cliente> obtenerTodosLosClientes();
    List<Cliente> obtenerClientePorApellido(String apellido);

    List<Cliente> obtenerClientePorApellidos(String paterno, String materno);

    List<Cliente> obtenerClientePorNombre(String nombre);

    Cliente obtenerClientePorTelefono(String telefono);
    Cliente obtenerClientePorCorreo(String correo);

    Cliente obtenerClientePorUsuario(String usuario);

    Cliente obtenerClientePorMascota(String nombreMascota);

    Cliente obtenerClientePorCita(Long idCita);

}
