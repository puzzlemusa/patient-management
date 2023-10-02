package com.cgm.patient_management.validator;

import com.cgm.patient_management.model.VisitType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class VisitTypeValidator implements ConstraintValidator<ValidVisitType, String> {

    List<String> states = List.of(
            VisitType.AT_HOME.name().toLowerCase(),
            VisitType.AT_THE_DOCTOR_OFFICE.name().toLowerCase()
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && states.contains(value.toLowerCase());
    }

}