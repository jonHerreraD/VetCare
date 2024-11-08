package com.v2.vetcare.controller;

import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.Pet;
import com.v2.vetcare.model.VetUser;
import com.v2.vetcare.request.*;
import com.v2.vetcare.response.ApiResponse;
import com.v2.vetcare.service.cliente.IClientService;
import com.v2.vetcare.service.pet.IPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/pets")
public class PetController {

    private final IPetService petService;
    private final IClientService clientService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllPets(){
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(new ApiResponse("Exito",pets));
    }


    @GetMapping("/pet/{id}/pets")
    public ResponseEntity<ApiResponse> getPetById(@PathVariable Long id){
        try {
            Pet pet = petService.getPetById(id);
            return ResponseEntity.ok(new ApiResponse("Exito!",pet));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @CrossOrigin("http://localhost:63342/")
    @GetMapping("/client/{clientId}/pets")
    public ResponseEntity<ApiResponse> getAllPetsByClient(@PathVariable Long clientId){
        try {
            Client client = clientService.getClientById(clientId);
            List<Pet> pets = petService.getAllPetsByClient(client);
            return ResponseEntity.ok(new ApiResponse("Exito!",pets));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @CrossOrigin("http://localhost:63342/")
    @PostMapping("/addPet")
    public ResponseEntity<ApiResponse> addPet(@RequestBody AddPetRequest request){
        System.out.println("Solicitud recibida: " + request.toString());
        System.out.println("Solicitud recibida: " + request.getPet());
        try {
            Client client = clientService.getClientById(request.getClient_id());
            Pet pet = petService.createPet(request.getPet(),client);
            return ResponseEntity.ok(new ApiResponse("Pet added",pet));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updatePet(@RequestBody UpdatePetRequest request){
        try {
            Pet pet = petService.updatePet(request);
            return  ResponseEntity.ok(new ApiResponse("Update successful!", pet));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @CrossOrigin("http://localhost:63342/")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deletePet(@PathVariable Long id){
        try {
            petService.deletePet(id);
            return  ResponseEntity.ok(new ApiResponse("Pet deleted!", id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
