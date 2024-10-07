package com.v2.vetcare.repository;

import com.v2.vetcare.model.Cliente;
import com.v2.vetcare.request.SolicitudAgregarCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente,Long> {
    @Query("SELECT c FROM Cliente c WHERE c.correo = :correo OR c.telefono = :telefono OR" +
            " (c.nombre = :nombre AND c.paterno = :paterno AND c.materno = :materno)")
    Optional<Cliente> encontrarClientePorSolicitud(@Param("correo") String correo,
                                                   @Param("telefono") String telefono,
                                                   @Param("nombre") String nombre,
                                                   @Param("paterno") String paterno,
                                                   @Param("materno") String materno);

}
