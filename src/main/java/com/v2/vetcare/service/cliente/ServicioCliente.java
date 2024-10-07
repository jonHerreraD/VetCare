package com.v2.vetcare.service.cliente;

import com.v2.vetcare.exceptions.AlreadyExistsException;
import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Cliente;
import com.v2.vetcare.repository.RepositorioCliente;
import com.v2.vetcare.request.SolicitudActualizarCliente;
import com.v2.vetcare.request.SolicitudAgregarCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioCliente implements IServicioCliente {

    private final RepositorioCliente repositorioCliente;
    @Override
    public Cliente registrarCliente(SolicitudAgregarCliente solicitudCliente) {

        if(repositorioCliente.encontrarClientePorSolicitud(solicitudCliente.getCorreo(),
                solicitudCliente.getTelefono(), solicitudCliente.getNombre(),
                solicitudCliente.getPaterno(), solicitudCliente.getMaterno()).isPresent()){
            throw new AlreadyExistsException("Cliente existente");
        }

        return repositorioCliente.save(crearCliente(solicitudCliente));
    }

    private Cliente crearCliente(SolicitudAgregarCliente solicitudCliente){
        return new Cliente(solicitudCliente.getNombre(), solicitudCliente.getPaterno(),
                solicitudCliente.getMaterno(),solicitudCliente.getTelefono(),
                solicitudCliente.getCorreo(), solicitudCliente.getDireccion());
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return repositorioCliente.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cliente no encontrado"));
    }

    @Override
    public void eliminarCliente(Long id) {
        repositorioCliente.findById(id)
                .ifPresentOrElse(repositorioCliente::delete,
                        ()->{throw new ResourceNotFoundException("Cliente no encontrado");});
    }

    @Override
    public Cliente actualizarCliente(SolicitudActualizarCliente solicitud) {
        return repositorioCliente.findById(solicitud.getId())
                .map(clienteExistente -> actualizarClienteExistente(clienteExistente,solicitud))
                .map(repositorioCliente :: save)
                .orElseThrow(()-> new ResourceNotFoundException("Cliente no encontrado"));
    }

    private Cliente actualizarClienteExistente(Cliente clienteExistente, SolicitudActualizarCliente solicitud){
        clienteExistente.setNombre(solicitud.getNombre());
        clienteExistente.setPaterno(solicitud.getPaterno());
        clienteExistente.setMaterno(solicitud.getMaterno());
        clienteExistente.setCorreo(solicitud.getCorreo());
        clienteExistente.setTelefono(solicitud.getTelefono());
        clienteExistente.setDireccion(solicitud.getDireccion());

        return clienteExistente;
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
       return repositorioCliente.findAll();
    }

    @Override
    public List<Cliente> obtenerClientePorApellido(String apellido) {
        return null;
    }

    @Override
    public List<Cliente> obtenerClientePorApellidos(String paterno, String materno) {
        return null;
    }

    @Override
    public List<Cliente> obtenerClientePorNombre(String nombre) {
        return null;
    }

    @Override
    public Cliente obtenerClientePorTelefono(String telefono) {
        return null;
    }

    @Override
    public Cliente obtenerClientePorCorreo(String correo) {
        return null;
    }

    @Override
    public Cliente obtenerClientePorUsuario(String usuario) {
        return null;
    }

    @Override
    public Cliente obtenerClientePorMascota(String nombreMascota) {
        return null;
    }

    @Override
    public Cliente obtenerClientePorCita(Long idCita) {
        return null;
    }
}
