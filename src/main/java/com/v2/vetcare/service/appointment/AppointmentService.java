package com.v2.vetcare.service.appointment;

import com.v2.vetcare.exceptions.AlreadyExistsException;
import com.v2.vetcare.model.*;
import com.v2.vetcare.repository.AppointmentRepository;
import com.v2.vetcare.repository.PetRepository;
import com.v2.vetcare.request.AddAppointmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;
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
    public List<AppointmentDTO> getAllAppointments() {
        List<Pet> pets = petRepository.findAll();
        List<Appointment> appointments = appointmentRepository.findAllByPets(pets);
        return appointments.stream()
                .map(AppointmentDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return null;
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByClient(Client client) {
        List<Pet> pets = petRepository.findByClient(client);
        List<Appointment> appointments = appointmentRepository.findAllByPets(pets);
        return appointments.stream()
                .map(AppointmentDTO::new)
                .collect(Collectors.toList());
    }


}
