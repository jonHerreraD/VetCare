package com.v2.vetcare.controller;

import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.VetUser;
import com.v2.vetcare.request.CreateVetUserRequest;
import com.v2.vetcare.response.ApiResponse;
import com.v2.vetcare.service.cliente.IClientService;
import com.v2.vetcare.service.usuario.IVetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/vetUsers")
public class VetUserController {

    private final IVetUserService vetUserService;
    private final IClientService clientService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllVetUsers(){
        List<VetUser> vetUsers = vetUserService.getAllVetUsers();
        return ResponseEntity.ok(new ApiResponse("EXito", vetUsers));
    }


    @GetMapping("/vetUser/{id}/vetUsers")
    public ResponseEntity<ApiResponse> getVetUserById(@PathVariable Long id){
        try {
            VetUser vetUser = vetUserService.getVetUserById(id);
            return ResponseEntity.ok(new ApiResponse("Exito", vetUser));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @CrossOrigin("http://localhost:63342/")
    @PostMapping( value = "/add", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<ApiResponse> createVetUser(@RequestBody CreateVetUserRequest request){
        try {
            Client client = clientService.getClientById(request.getClient_id());
            VetUser vetUser = vetUserService.createVetUser(request.getVetUser(),client);
            return ResponseEntity.ok(new ApiResponse("Usuario creado",vetUser));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateVetUser(@RequestBody VetUser vetUser){
        try {
            VetUser usu = vetUserService.updateVetUser(vetUser);
            return ResponseEntity.ok(new ApiResponse("Usuario actualizado",usu));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteVetUser(@RequestBody Long id){
        try {
            vetUserService.deleteVetUser(id);
            return  ResponseEntity.ok(new ApiResponse("Usuario Eliminado!", id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
