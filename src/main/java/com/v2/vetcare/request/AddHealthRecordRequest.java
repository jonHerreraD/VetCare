package com.v2.vetcare.request;


import com.v2.vetcare.model.HealthRecord;
import lombok.Data;

@Data
public class AddHealthRecordRequest {
    private HealthRecord healthRecord;
    private Long pet_id;
}
