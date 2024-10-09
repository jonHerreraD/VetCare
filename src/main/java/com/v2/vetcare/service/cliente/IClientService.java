package com.v2.vetcare.service.cliente;


import com.v2.vetcare.model.Client;
import com.v2.vetcare.request.UpdateClientRequest;
import com.v2.vetcare.request.AddClientRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClientService {
    Client addClient(AddClientRequest addClientRequest);
    Client getClientById(Long id);
    void deleteClient(Long id);
    Client updateClient(UpdateClientRequest updateClientRequest);

    List<Client> getAllClients();
    List<Client> getClientByLastName(String lastName);

    List<Client> getClientByPaternalAndMaternal(String paternal, String maternal);

    List<Client> getClientByName(String name);

    Client getClientByPhoneNumber(String phoneNumber);
    Client getClientByEmail(String address);

    Client getClientByUsername(String username);

    Client getClientByPetName(String petName);

    Client getClientByAppointment(Long appointment_id);

}
