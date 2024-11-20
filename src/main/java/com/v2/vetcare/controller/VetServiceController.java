package com.v2.vetcare.controller;

import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.VetService;
import com.v2.vetcare.request.AddServiceRequest;
import com.v2.vetcare.response.ApiResponse;
import com.v2.vetcare.service.vetservice.IVetServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/vetServices")
public class VetServiceController {

    private final IVetServiceService vetServiceService;

    @CrossOrigin("http://localhost:63342/")
    @PostMapping( value = "/add", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<ApiResponse> addService(@RequestBody AddServiceRequest addServiceRequest){
        try{
            VetService vetService = vetServiceService.addService(addServiceRequest);
            return ResponseEntity.ok(new ApiResponse("Vet service added",vetService));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
