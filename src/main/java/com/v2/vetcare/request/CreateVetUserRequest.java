package com.v2.vetcare.request;

import com.v2.vetcare.model.VetUser;
import lombok.Data;

@Data
public class CreateVetUserRequest {
    private VetUser vetUser;
    private Long client_id;
    private Long employee_id;
}