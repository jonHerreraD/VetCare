package com.v2.vetcare.service.appointment;

import com.v2.vetcare.exceptions.AlreadyExistsException;
import com.v2.vetcare.model.Appointment;
import com.v2.vetcare.model.HealthRecord;
import com.v2.vetcare.model.Pet;
import com.v2.vetcare.model.VetService;
import com.v2.vetcare.repository.AppointmentRepository;
import com.v2.vetcare.request.AddAppointmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    @Override
    public Appointment addAppointment(Appointment appointment, Pet pet, HealthRecord healthRecord, VetService vetService) {
        return Optional.of(appointment)
                .filter(a -> !appointmentRepository.existsByDateAndHour(
                        appointment.getDate(), appointment.getHour()))
                .map(a -> {
                    a.setPet(pet);
                    a.setHealthRecord(healthRecord);
                    a.setVetService(vetService);
                    return appointmentRepository.save(a);
                })
                .orElseThrow(() -> new AlreadyExistsException("Appointment already exists"));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return null;
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return null;
    }
}
