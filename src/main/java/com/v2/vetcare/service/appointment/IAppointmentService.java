package com.v2.vetcare.service.appointment;

import com.v2.vetcare.model.*;
import com.v2.vetcare.request.AddAppointmentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAppointmentService {

    Appointment addAppointment(Appointment appointment, Pet pet,
                               HealthRecord healthRecord, VetService vetService);

    List<AppointmentDTO> getAllAppointments();

    Appointment getAppointmentById(Long id);

    List<AppointmentDTO> getAllAppointmentsByClient(Client client);

}
