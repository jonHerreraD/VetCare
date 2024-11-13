package com.v2.vetcare.repository;

import com.v2.vetcare.model.ClinicHistory;
import com.v2.vetcare.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicHistoryRepository extends JpaRepository<ClinicHistory,Long> {

    boolean existsByNameAndPet(String name, Pet pet);

    ClinicHistory findClinicHistoryByPet(Pet pet);
}
