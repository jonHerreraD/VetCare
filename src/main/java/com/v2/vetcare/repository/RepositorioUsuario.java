package com.v2.vetcare.repository;

import com.v2.vetcare.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
    boolean existsByUsuario(String usuario);

    Optional<Usuario> findByUsernameAndPassword(String username, String password);
}
