package com.v2.vetcare.repository;



import com.v2.vetcare.model.VetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VetUserRepository extends JpaRepository<VetUser, Long> {
    boolean existsByUsername(String username);

    Optional<VetUser> findVetUserByUsernameAndPassword(String username, String password);
}
