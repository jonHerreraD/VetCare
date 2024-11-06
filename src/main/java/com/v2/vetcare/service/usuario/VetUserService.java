package com.v2.vetcare.service.usuario;

import com.v2.vetcare.exceptions.AlreadyExistsException;
import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.VetUser;
import com.v2.vetcare.repository.VetUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VetUserService implements IVetUserService {

    private final VetUserRepository vetUserRepository;
    @Override
    public VetUser createVetUser(VetUser vetUser, Client client) {
        return Optional.of(vetUser)
                .filter(u -> !vetUserRepository.existsByUsername(u.getUsername()))
                .map(u -> {
                    u.setClient(client);
                    return vetUserRepository.save(u);
                })
                .orElseThrow(() -> new AlreadyExistsException("El nombre de usuario ya está en uso"));
    }

    @Override
    public VetUser getVetUserById(Long id) {
        return vetUserRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No existe el usuario"));
    }

    @Override
    public void deleteVetUser(Long id) {
        vetUserRepository.findById(id)
                .ifPresentOrElse(vetUserRepository::delete,
                        ()->{throw new ResourceNotFoundException("Usuario no encontrado");});
    }

    @Override
    public VetUser updateVetUser(VetUser vetUser) {
        return Optional.ofNullable(getVetUserById(vetUser.getId()))
                .map(OlderVetUser ->{
                    OlderVetUser.setUsername(vetUser.getUsername());
                    OlderVetUser.setPassword(vetUser.getPassword());
                    OlderVetUser.setRole(vetUser.getRole());
                    return vetUserRepository.save(OlderVetUser);
                }).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado!"));
    }

    @Override
    public List<VetUser> getAllVetUsers() {
        return vetUserRepository.findAll();
    }

    @Override
    public VetUser findVetUserByUsernameAndPassword(String username, String password) {
        return vetUserRepository.findVetUserByUsernameAndPassword(username,password)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    @Override
    public Long getClientIdByUsername(String username) {
        return vetUserRepository.getClientIdByUsername(username);
    }
}

