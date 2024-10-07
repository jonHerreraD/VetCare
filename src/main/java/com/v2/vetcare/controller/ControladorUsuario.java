package com.v2.vetcare.controller;

import com.v2.vetcare.exceptions.AlreadyExistsException;
import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Cliente;
import com.v2.vetcare.model.Usuario;
import com.v2.vetcare.response.ApiResponse;
import com.v2.vetcare.service.usuario.IServicioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/usuarios")
public class ControladorUsuario {

    private final IServicioUsuario servicioUsuario;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> obtenerTodosLosUsuarios(){
        List<Usuario> usuarios = servicioUsuario.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(new ApiResponse("EXito", usuarios));
    }


    @GetMapping("/usuario/{id}/usuarios")
    public ResponseEntity<ApiResponse> obtenerUsuarioPorId(@PathVariable Long id){
        try {
            Usuario usuario = servicioUsuario.obtenerUsuarioPorId(id);
            return ResponseEntity.ok(new ApiResponse("Exito", usuario));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("/crearUsuario")
    public ResponseEntity<ApiResponse> crearUsuario(@RequestBody Usuario usuario, @RequestBody Cliente cliente){
        try {
            Usuario usu = servicioUsuario.crearUsuario(usuario,cliente);
            return ResponseEntity.ok(new ApiResponse("Usuario creado",usu));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ApiResponse> actualizarUsuario(@RequestBody Usuario usuario){
        try {
            Usuario usu = servicioUsuario.actualizarUsuario(usuario);
            return ResponseEntity.ok(new ApiResponse("Usuario actualizado",usu));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<ApiResponse> eliminarUsuario(@RequestBody Long id){
        try {
            servicioUsuario.eliminarUsuario(id);
            return  ResponseEntity.ok(new ApiResponse("Usuario Eliminado!", id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
