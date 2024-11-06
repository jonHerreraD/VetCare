package com.v2.vetcare.service.usuario;

import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.VetUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IVetUserService {

    VetUser createVetUser(VetUser vetUser, Client client);
    VetUser getVetUserById(Long id);
    void deleteVetUser(Long id);
    VetUser updateVetUser(VetUser vetUser);

    List<VetUser> getAllVetUsers();

    VetUser findVetUserByUsernameAndPassword(String username, String password);
    Long getClientIdByUsername(String username);


}
