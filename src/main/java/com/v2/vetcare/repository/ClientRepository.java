package com.v2.vetcare.repository;

import com.v2.vetcare.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("SELECT c FROM Client c WHERE c.address = :address OR c.phoneNumber = :phoneNumber OR" +
            " (c.name = :name AND c.paternal = :paternal AND c.maternal = :maternal)")
    Optional<Client> findClientByRequest(@Param("email") String email,
                                                   @Param("phoneNumber") String phoneNumber,
                                                   @Param("name") String name,
                                                   @Param("paternal") String paternal,
                                                   @Param("maternal") String maternal);

}
