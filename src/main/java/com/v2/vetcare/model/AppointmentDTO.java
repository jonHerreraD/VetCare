package com.v2.vetcare.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AppointmentDTO {
    private Long id;
    private LocalDate date;
    private LocalTime hour;
    private String status;
    private String petName;
    private String clientName;
    private String service;

    public AppointmentDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.date = appointment.getDate();
        this.hour = appointment.getHour();
        this.status = appointment.getStatus();
        this.petName = appointment.getPet() != null ? appointment.getPet().getName() : "No asignada";
        this.clientName = appointment.getPet().getClient() != null ? appointment.getPet().getClient().getName() + " "
                + appointment.getPet().getClient().getPaternal() + " "
                + appointment.getPet().getClient().getMaternal() : "No asignado";
        this.service = appointment.getVetService() != null ? appointment.getVetService().getRequiredService(): "No asignado";
    }



}
