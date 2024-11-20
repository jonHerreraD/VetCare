package com.v2.vetcare.controller;

import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.HealthRecord;
import com.v2.vetcare.model.Pet;
import com.v2.vetcare.request.AddHealthRecordRequest;
import com.v2.vetcare.response.ApiResponse;
import com.v2.vetcare.service.clinicHistory.IHealthRecordService;
import com.v2.vetcare.service.pet.IPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/healthRecords")
public class HealthRecordController {

    private final IHealthRecordService healthRecordService;
    private final IPetService petService;

    @CrossOrigin("http://localhost:63342/")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllHealthRecords(){
        List<HealthRecord> records = healthRecordService.getAllHealthRecords();
        return ResponseEntity.ok(new ApiResponse("Success",records));
    }

    @CrossOrigin("http://localhost:63342/")
    @GetMapping("/pet/{petId}")
    public ResponseEntity<ApiResponse> getHealthRecordByPet(@PathVariable Long petId){
        try {
            Pet pet = petService.getPetById(petId);
            HealthRecord healthRecord = healthRecordService.findHealthRecordByPet(pet);
            return ResponseEntity.ok(new ApiResponse("Success!",healthRecord));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @CrossOrigin("http://localhost:63342/")
    @PostMapping( value = "/add", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<ApiResponse> createHealthRecord(@RequestBody AddHealthRecordRequest request){
        try {
            Pet pet= petService.getPetById(request.getPet_id());
            HealthRecord healthRecord = healthRecordService.createHealthRecord(request.getHealthRecord(),pet);
            return ResponseEntity.ok(new ApiResponse("Health Record created",healthRecord));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }



}
