package com.v2.vetcare.request;

import com.v2.vetcare.model.ClinicHistory;
import lombok.Data;

@Data
public class AddClinicHistoryRequest {
    private ClinicHistory clinicHistory;
    private Long pet_id;
}
