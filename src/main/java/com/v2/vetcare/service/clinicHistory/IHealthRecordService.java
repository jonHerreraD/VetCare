package com.v2.vetcare.service.clinicHistory;


import com.v2.vetcare.model.HealthRecord;
import com.v2.vetcare.model.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IHealthRecordService {

    HealthRecord createHealthRecord(HealthRecord healthRecord, Pet pet);

    HealthRecord getHealthRecordById(Long id);

    List<HealthRecord> getAllHealthRecords();

    HealthRecord findHealthRecordByPet(Pet pet);

}
