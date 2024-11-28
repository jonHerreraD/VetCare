package com.v2.vetcare.repository;

import com.v2.vetcare.model.Appointment;
import com.v2.vetcare.model.AppointmentDTO;
import com.v2.vetcare.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    boolean existsByDateAndHour(LocalDate date, LocalTime hour);

    @Query("SELECT a FROM Appointment a WHERE a.pet IN :pets")
    List<Appointment> findAllByPets(@Param("pets") List<Pet> pets);
}
