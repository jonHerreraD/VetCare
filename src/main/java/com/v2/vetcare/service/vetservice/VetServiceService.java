package com.v2.vetcare.service.vetservice;
import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.VetService;
import com.v2.vetcare.repository.VetServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.v2.vetcare.request.AddServiceRequest;


import java.util.List;

@Service
@RequiredArgsConstructor
public class VetServiceService implements IVetServiceService {

    private final VetServiceRepository vetServiceRepository;
    @Override
    public VetService addService(AddServiceRequest addServiceRequest) {
        VetService vetService = addServiceRequest.getService();
        return vetServiceRepository.save(vetService);
    }

    @Override
    public VetService getServiceById(Long id) {
        return vetServiceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Vet service not found"));
    }

    @Override
    public List<VetService> getAllServices() {
        return vetServiceRepository.findAll();
    }
}
