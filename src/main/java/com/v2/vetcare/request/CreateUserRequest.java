package com.v2.vetcare.request;


import com.v2.vetcare.model.User;
import lombok.Data;

@Data
public class CreateUserRequest {
    private User user;
    private Long client_id;
}
