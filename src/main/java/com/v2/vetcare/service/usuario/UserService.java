package com.v2.vetcare.service.usuario;

import com.v2.vetcare.exceptions.AlreadyExistsException;
import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.User;
import com.v2.vetcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    @Override
    public User createUser(User user, Client client) {
        return Optional.of(user)
                .filter(u -> !userRepository.existsByUsername(u.getUsername()))
                .map(u -> {
                    u.setClient(client);
                    return userRepository.save(u);
                })
                .orElseThrow(() -> new AlreadyExistsException("El nombre de usuario ya está en uso"));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No existe el usuario"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .ifPresentOrElse(userRepository::delete,
                        ()->{throw new ResourceNotFoundException("Usuario no encontrado");});
    }

    @Override
    public User updateUser(User user) {
        return Optional.ofNullable(getUserById(user.getId()))
                .map(OlderUser ->{
                    OlderUser.setUsername(user.getUsername());
                    OlderUser.setPassword(user.getPassword());
                    OlderUser.setRole(user.getRole());
                    return userRepository.save(OlderUser);
                }).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado!"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username,password)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }
}
