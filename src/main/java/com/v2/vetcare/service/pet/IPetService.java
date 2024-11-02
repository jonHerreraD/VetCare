package com.v2.vetcare.service.pet;

import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.Pet;

import com.v2.vetcare.request.UpdatePetRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPetService {
    Pet createPet(Pet pet, Client client);
    Pet getPetById(Long id);
    void deletePet(Long id);
    Pet updatePet(UpdatePetRequest request);

    List<Pet> getAllPetsByClient(Client client);
    List<Pet> getAllPets();

}
