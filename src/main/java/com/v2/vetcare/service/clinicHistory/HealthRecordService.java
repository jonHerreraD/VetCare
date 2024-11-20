package com.v2.vetcare.service.clinicHistory;

import com.v2.vetcare.exceptions.AlreadyExistsException;
import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.HealthRecord;
import com.v2.vetcare.model.Pet;
import com.v2.vetcare.repository.HealthRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HealthRecordService implements IHealthRecordService {

    private final HealthRecordRepository healthRecordRepository;
    @Override
    public HealthRecord createHealthRecord(HealthRecord healthRecord, Pet pet) {
        return Optional.of(healthRecord)
                .filter(h -> !healthRecordRepository.existsByNameAndPet(healthRecord.getName(), pet))
                .map(h -> {
                    h.setPet(pet);
                    return healthRecordRepository.save(h);
                })
                .orElseThrow(() -> new AlreadyExistsException("Health Record of " + pet.getName()
                        + " already exists"));
    }

    @Override
    public HealthRecord getHealthRecordById(Long id) {
        return healthRecordRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Health Record not found"));
    }

    @Override
    public List<HealthRecord> getAllHealthRecords() {
        return healthRecordRepository.findAll();
    }

    @Override
    public HealthRecord findHealthRecordByPet(Pet pet) {
        return healthRecordRepository.findHealthRecordByPet(pet);
    }
}
