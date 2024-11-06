package com.v2.vetcare.service.cliente;

import com.v2.vetcare.exceptions.AlreadyExistsException;
import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Client;
import com.v2.vetcare.repository.ClientRepository;
import com.v2.vetcare.repository.VetUserRepository;
import com.v2.vetcare.request.UpdateClientRequest;
import com.v2.vetcare.request.AddClientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private final VetUserRepository vetUserRepository;
    @Override
    public Client addClient(AddClientRequest request) {

        if(clientRepository.findClientByRequest(request.getEmail(),
                request.getPhoneNumber(), request.getName(),
                request.getPaternal(), request.getMaternal(), request.getAddress()).isPresent()){
            throw new AlreadyExistsException("Existent Client");
        }

        return clientRepository.save(createClient(request));
    }

    private Client createClient(AddClientRequest request){
        return new Client(request.getName(), request.getPaternal(),
                request.getMaternal(),request.getPhoneNumber(),
                request.getEmail(), request.getAddress());
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Client not found"));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.findById(id)
                .ifPresentOrElse(clientRepository::delete,
                        ()->{throw new ResourceNotFoundException("Client not found");});
    }

    @Override
    public Client updateClient(UpdateClientRequest request) {
        return clientRepository.findById(request.getId())
                .map(existentClient -> updateExistentClient(existentClient,request))
                .map(clientRepository:: save)
                .orElseThrow(()-> new ResourceNotFoundException("Cliente no encontrado"));
    }

    private Client updateExistentClient(Client existentClient, UpdateClientRequest request){
        existentClient.setName(request.getName());
        existentClient.setPaternal(request.getPaternal());
        existentClient.setMaternal(request.getMaternal());
        existentClient.setEmail(request.getEmail());
        existentClient.setPhoneNumber(request.getPhoneNumber());
        existentClient.setAddress(request.getAddress());

        return existentClient;
    }

    @Override
    public List<Client> getAllClients() {
       return clientRepository.findAll();
    }

    @Override
    public List<Client> getClientByLastName(String lastName) {
        return null;
    }

    @Override
    public List<Client> getClientByPaternalAndMaternal(String paternal, String maternal) {
        return null;
    }

    @Override
    public List<Client> getClientByName(String name) {
        return null;
    }

    @Override
    public Client getClientByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public Client getClientByEmail(String email) {
        return null;
    }

    @Override
    public Client getClientByUsername(String username) {
        return null;
    }

    @Override
    public Client getClientByPetName(String petName) {
        return null;
    }

    @Override
    public Client getClientByAppointment(Long appointmentId) {
        return null;
    }

    @Override
    public Client findClientByUsername(String username) {
        return clientRepository.findClientByUsername(username);
    }
}
