package com.v2.vetcare.controller;

import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Client;
import com.v2.vetcare.request.UpdateClientRequest;
import com.v2.vetcare.request.AddClientRequest;
import com.v2.vetcare.response.ApiResponse;
import com.v2.vetcare.service.cliente.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/clients")
public class ClientController {

    private final IClientService clientService;

    @CrossOrigin("http://localhost:63342/")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllClients(){
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(new ApiResponse("Exito",clients));
    }


    @CrossOrigin("http://localhost:63342/")
    @GetMapping("/client/{id}/clients")
    public ResponseEntity<ApiResponse> getClientById(@PathVariable Long id){
        try {
            Client client = clientService.getClientById(id);
            return ResponseEntity.ok(new ApiResponse("Exito!",client));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @CrossOrigin("http://localhost:63342/")
    @GetMapping("/findByUsername")
    public ResponseEntity<ApiResponse> findClientByUsername(@RequestParam String username){
        try {
            Client client = clientService.findClientByUsername(username);
            return  ResponseEntity.ok(new ApiResponse("Cliente Encontrado!", client));
        }catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @CrossOrigin("http://localhost:63342/")
    @PostMapping("/add")
    public  ResponseEntity<ApiResponse> addClient(@RequestBody AddClientRequest request){
        try {
            Client client = clientService.addClient(request);
            return  ResponseEntity.ok(new ApiResponse("Cliente Registrado!", client));
        }catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateClient(@RequestBody UpdateClientRequest request){
        try {
            Client client = clientService.updateClient(request);
            return  ResponseEntity.ok(new ApiResponse("Actualizacion exitosa!", client));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/delete/{clientId}/clients")
    public ResponseEntity<ApiResponse> deleteClient(@PathVariable Long clientId){
        try {
            clientService.deleteClient(clientId);
            return  ResponseEntity.ok(new ApiResponse("Cliente Eliminado!", clientId));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }




}
