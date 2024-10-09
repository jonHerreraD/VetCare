package com.v2.vetcare.request;

import lombok.Data;

@Data
public class AddClientRequest {
    private String name;
    private String paternal;
    private String maternal;
    private String phoneNumber;
    private String email;
    private String address;
}