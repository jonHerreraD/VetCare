package com.v2.vetcare.repository;


import com.v2.vetcare.model.VetService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetServiceRepository extends JpaRepository<VetService,Long> {
}
