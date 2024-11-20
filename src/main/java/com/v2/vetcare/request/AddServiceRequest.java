package com.v2.vetcare.request;


import com.v2.vetcare.model.VetService;
import lombok.Data;

@Data
public class AddServiceRequest {
    private VetService service;
}
