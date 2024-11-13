package com.v2.vetcare.service.clinicHistory;

import com.v2.vetcare.model.ClinicHistory;
import com.v2.vetcare.model.Pet;
import com.v2.vetcare.request.AddClinicHistoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClinicHistoryService {

    ClinicHistory createClinicHistory(ClinicHistory clinicHistory, Pet pet);

    ClinicHistory getClinicHistoryById(Long id);

    List<ClinicHistory> getAllClinicHistories();

    ClinicHistory findClinicHistoryByPet(Pet pet);

}
