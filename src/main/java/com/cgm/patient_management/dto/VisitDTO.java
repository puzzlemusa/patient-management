package com.cgm.patient_management.dto;

import com.cgm.patient_management.validator.ValidLocalDateTime;
import com.cgm.patient_management.validator.ValidVisitReason;
import com.cgm.patient_management.validator.ValidVisitType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitDTO {

    private Long id;
    @NotNull(message = "Patient id cannot be null")
    private Long patientId;
    @ValidLocalDateTime
    private String dateTime;
    @ValidVisitType
    private String visitType;
    @ValidVisitReason
    private String visitReason;
    private String familyHistory;
}
