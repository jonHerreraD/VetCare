package com.v2.vetcare.controller;

import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.ClinicHistory;
import com.v2.vetcare.model.Pet;
import com.v2.vetcare.model.VetUser;
import com.v2.vetcare.request.AddClinicHistoryRequest;
import com.v2.vetcare.request.CreateVetUserRequest;
import com.v2.vetcare.response.ApiResponse;
import com.v2.vetcare.service.clinicHistory.IClinicHistoryService;
import com.v2.vetcare.service.pet.IPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/healthRecords")
public class ClinicHistoryController {

    private final IClinicHistoryService clinicHistoryService;
    private final IPetService petService;

    @CrossOrigin("http://localhost:63342/")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllClinicHistories(){
        List<ClinicHistory> histories = clinicHistoryService.getAllClinicHistories();
        return ResponseEntity.ok(new ApiResponse("Exito",histories));
    }

    @CrossOrigin("http://localhost:63342/")
    @GetMapping("/pet/{petId}")
    public ResponseEntity<ApiResponse> getClinicHistoryByPet(@PathVariable Long petId){
        try {
            Pet pet = petService.getPetById(petId);
            ClinicHistory clinicHistory = clinicHistoryService.findClinicHistoryByPet(pet);
            return ResponseEntity.ok(new ApiResponse("Exito!",clinicHistory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @CrossOrigin("http://localhost:63342/")
    @PostMapping( value = "/add", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<ApiResponse> createClinicHistory(@RequestBody AddClinicHistoryRequest request){
        try {
            Pet pet= petService.getPetById(request.getPet_id());
            ClinicHistory clinicHistory = clinicHistoryService.createClinicHistory(request.getClinicHistory(),pet);
            return ResponseEntity.ok(new ApiResponse("Clinic History created",clinicHistory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }



}
