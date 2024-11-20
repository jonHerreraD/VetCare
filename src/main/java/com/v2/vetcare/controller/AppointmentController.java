package com.v2.vetcare.controller;

import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Appointment;
import com.v2.vetcare.model.HealthRecord;
import com.v2.vetcare.model.Pet;
import com.v2.vetcare.model.VetService;
import com.v2.vetcare.request.AddAppointmentRequest;
import com.v2.vetcare.response.ApiResponse;
import com.v2.vetcare.service.appointment.IAppointmentService;
import com.v2.vetcare.service.clinicHistory.IHealthRecordService;
import com.v2.vetcare.service.pet.IPetService;
import com.v2.vetcare.service.vetservice.IVetServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/appointments")
public class AppointmentController {

    private final IAppointmentService appointmentService;
    private final IPetService petService;
    private final IHealthRecordService healthRecordService;
    private final IVetServiceService vetServiceService;

    @CrossOrigin("http://localhost:63342/")
    @PostMapping( value = "/add", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<ApiResponse> addAppointment(@RequestBody AddAppointmentRequest request){
        try {
            Pet pet = petService.getPetById(request.getPet_id());
            HealthRecord healthRecord = healthRecordService.getHealthRecordById(request.getHealthRecord_id());
            VetService vetService = vetServiceService.getServiceById(request.getVetService_id());
            Appointment appointment = appointmentService.addAppointment(request.getAppointment(),
                    pet,healthRecord,vetService);
            return  ResponseEntity.ok(new ApiResponse(" Appointment added!", appointment));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
