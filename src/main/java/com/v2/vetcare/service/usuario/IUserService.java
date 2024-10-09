package com.v2.vetcare.service.usuario;

import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    User createUser(User user, Client client);
    User getUserById(Long id);
    void deleteUser(Long id);
    User updateUser(User user);

    List<User> getAllUsers();

    User findUserByUsernameAndPassword(String username, String password);
}
