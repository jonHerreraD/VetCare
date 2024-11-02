package com.v2.vetcare.repository;

import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {
     boolean existsByClient(Client client);

     List<Pet> findByClient(Client client);
}
