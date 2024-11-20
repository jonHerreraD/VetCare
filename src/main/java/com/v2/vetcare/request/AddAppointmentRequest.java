package com.v2.vetcare.request;

import com.v2.vetcare.model.Appointment;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AddAppointmentRequest {
    private Appointment appointment;
    private Long pet_id;
    private Long vetService_id;
    private Long healthRecord_id;
}
