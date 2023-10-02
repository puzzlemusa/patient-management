package com.cgm.patient_management.validator;

import com.cgm.patient_management.model.VisitReason;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class VisitReasonValidator implements ConstraintValidator<ValidVisitReason, String> {

    List<String> states = List.of(
            VisitReason.FIRST_VISIT.name().toLowerCase(),
            VisitReason.RECURRING_VISIT.name().toLowerCase(),
            VisitReason.URGENT.name().toLowerCase()
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && states.contains(value.toLowerCase());
    }

}