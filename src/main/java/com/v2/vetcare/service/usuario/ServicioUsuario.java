package com.v2.vetcare.service.usuario;

import com.v2.vetcare.exceptions.AlreadyExistsException;
import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Cliente;
import com.v2.vetcare.model.Usuario;
import com.v2.vetcare.repository.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioUsuario implements IServicioUsuario{

    private final RepositorioUsuario repositorioUsuario;
    @Override
    public Usuario crearUsuario(Usuario usuario, Cliente cliente) {
        return Optional.of(usuario)
                .filter(u -> !repositorioUsuario.existsByUsuario(u.getUsuario()))
                .map(u -> {
                    u.setCliente(cliente);
                    return repositorioUsuario.save(u);
                })
                .orElseThrow(() -> new AlreadyExistsException("El nombre de usuario ya está en uso"));
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return repositorioUsuario.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No existe el usuario"));
    }

    @Override
    public void eliminarUsuario(Long id) {
        repositorioUsuario.findById(id)
                .ifPresentOrElse(repositorioUsuario::delete,
                        ()->{throw new ResourceNotFoundException("Usuario no encontrado");});
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return Optional.ofNullable(obtenerUsuarioPorId(usuario.getId()))
                .map(usuarioAnterior ->{
                    usuarioAnterior.setUsuario(usuario.getUsuario());
                    usuarioAnterior.setContrasenia(usuario.getContrasenia());
                    usuarioAnterior.setRol(usuario.getRol());
                    return repositorioUsuario.save(usuarioAnterior);
                }).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado!"));
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return repositorioUsuario.findAll();
    }

    @Override
    public Usuario findByUsernameAndPassword(String username, String pass) {
        return repositorioUsuario.findByUsernameAndPassword(username,pass)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }
}
