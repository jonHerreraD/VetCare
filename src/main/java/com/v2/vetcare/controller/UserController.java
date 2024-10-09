package com.v2.vetcare.controller;

import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.User;
import com.v2.vetcare.request.CreateUserRequest;
import com.v2.vetcare.response.ApiResponse;
import com.v2.vetcare.service.cliente.IClientService;
import com.v2.vetcare.service.usuario.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/usuarios")
public class UserController {

    private final IUserService userService;
    private final IClientService clientService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse("EXito", users));
    }


    @GetMapping("/usuario/{id}/usuarios")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id){
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(new ApiResponse("Exito", user));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @CrossOrigin("http://localhost:63342/")
    @PostMapping("/crearUsuario")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request){
        try {
            Client client = clientService.getClientById(request.getClient_id());
            User user = userService.createUser(request.getUser(),client);
            return ResponseEntity.ok(new ApiResponse("Usuario creado",user));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody User user){
        try {
            User usu = userService.updateUser(user);
            return ResponseEntity.ok(new ApiResponse("Usuario actualizado",usu));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<ApiResponse> deleteUser(@RequestBody Long id){
        try {
            userService.deleteUser(id);
            return  ResponseEntity.ok(new ApiResponse("Usuario Eliminado!", id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
