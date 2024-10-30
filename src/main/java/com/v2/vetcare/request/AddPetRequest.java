package com.v2.vetcare.request;

import com.v2.vetcare.model.Pet;
import lombok.Data;

@Data
public class AddPetRequest {
    private Pet pet;
    private Long client_id;
}
