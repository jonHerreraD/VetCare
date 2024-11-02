package com.v2.vetcare.service.pet;

import com.v2.vetcare.exceptions.AlreadyExistsException;
import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.Pet;
import com.v2.vetcare.repository.PetRepository;
import com.v2.vetcare.request.UpdatePetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService implements IPetService{

    private final PetRepository petRepository;
    @Override
    public Pet createPet(Pet pet, Client client) {
        return Optional.of(pet)
                .filter(p -> !petRepository.existsByClient(client))
                .map(p -> {
                    p.setClient(client);
                    return petRepository.save(p);
                })
                .orElseThrow(() -> new AlreadyExistsException("Pet already exists for client " + client.getName()));
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Pet doesn't exists"));
    }

    @Override
    public void deletePet(Long id) {
        petRepository.findById(id)
                .ifPresentOrElse(petRepository::delete,
                        ()->{throw new ResourceNotFoundException("Pet not found");});
    }

    @Override
    public Pet updatePet(UpdatePetRequest pet) {
        return Optional.ofNullable(getPetById(pet.getId()))
                .map(OlderPet ->{
                    OlderPet.setName(pet.getName());
                    OlderPet.setSpecie(pet.getSpecie());
                    OlderPet.setBreed(pet.getBreed());
                    OlderPet.setSex(pet.getSex());
                    OlderPet.setAge(pet.getAge());
                    OlderPet.setWeight(pet.getWeight());
                    OlderPet.setCharacteristics(pet.getCharacteristics());
                    return petRepository.save(OlderPet);
                }).orElseThrow(() -> new ResourceNotFoundException("Pet not found!"));
    }

    @Override
    public List<Pet> getAllPetsByClient(Client client) {
        return petRepository.findByClient(client);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
}
