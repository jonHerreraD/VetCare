package com.v2.vetcare.repository;

import com.v2.vetcare.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente,Long> {
}
