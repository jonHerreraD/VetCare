package com.v2.vetcare.service.vetservice;


import com.v2.vetcare.model.VetService;
import com.v2.vetcare.request.AddServiceRequest;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface IVetServiceService {

    VetService addService(AddServiceRequest addServiceRequest);

    VetService getServiceById(Long id);

    List<VetService> getAllServices();


}
