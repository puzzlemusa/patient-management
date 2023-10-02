package com.cgm.patient_management.dto;

import com.cgm.patient_management.validator.ValidLocalDate;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {

    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Surname cannot be blank")
    private String surname;
    @ValidLocalDate
    private String dateOfBirth;
    private String socialSecurityNumber;
}
