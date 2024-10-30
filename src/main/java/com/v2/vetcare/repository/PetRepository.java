package com.v2.vetcare.repository;

import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet,Long> {
     boolean existsForClient(Client client);

     List<Pet> findAllForClient(Client client);
}
