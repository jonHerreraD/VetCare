package com.v2.vetcare.repository;


import com.v2.vetcare.model.HealthRecord;
import com.v2.vetcare.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord,Long> {

    boolean existsByNameAndPet(String name, Pet pet);

    HealthRecord findHealthRecordByPet(Pet pet);
}
