package com.v2.vetcare.request;

import lombok.Data;

@Data
public class UpdatePetRequest {
    private Long id;
    private String name;
    private String specie;
    private String breed;
    private String sex;
    private String characteristics;
    private int age;
    private float weight;
}
