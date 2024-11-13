package com.v2.vetcare.service.clinicHistory;

import com.v2.vetcare.exceptions.AlreadyExistsException;
import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.ClinicHistory;
import com.v2.vetcare.model.Pet;
import com.v2.vetcare.repository.ClinicHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClinicHistoryService implements IClinicHistoryService{

    private final ClinicHistoryRepository clinicHistoryRepository;
    @Override
    public ClinicHistory createClinicHistory(ClinicHistory clinicHistory, Pet pet) {
        return Optional.of(clinicHistory)
                .filter(c -> !clinicHistoryRepository.existsByNameAndPet(clinicHistory.getName(), pet))
                .map(c -> {
                    c.setPet(pet);
                    return clinicHistoryRepository.save(c);
                })
                .orElseThrow(() -> new AlreadyExistsException("Clinic history of " + pet.getName()
                        + " already exists"));
    }

    @Override
    public ClinicHistory getClinicHistoryById(Long id) {
        return clinicHistoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Clinic History not found"));
    }

    @Override
    public List<ClinicHistory> getAllClinicHistories() {
        return clinicHistoryRepository.findAll();
    }

    @Override
    public ClinicHistory findClinicHistoryByPet(Pet pet) {
        return clinicHistoryRepository.findClinicHistoryByPet(pet);
    }
}
