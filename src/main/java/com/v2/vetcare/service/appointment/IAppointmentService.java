package com.v2.vetcare.service.appointment;

import com.v2.vetcare.model.Appointment;
import com.v2.vetcare.model.HealthRecord;
import com.v2.vetcare.model.Pet;
import com.v2.vetcare.model.VetService;
import com.v2.vetcare.request.AddAppointmentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAppointmentService {

    Appointment addAppointment(Appointment appointment, Pet pet,
                               HealthRecord healthRecord, VetService vetService);

    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(Long id);

}
