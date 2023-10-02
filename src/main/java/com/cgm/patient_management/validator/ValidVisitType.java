package com.cgm.patient_management.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.FIELD
})
@Constraint(validatedBy = VisitTypeValidator.class)
public @interface ValidVisitType {

    String message() default "Invalid visit type. Valid visit types are: AT_HOME, AT_THE_DOCTOR_OFFICE";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
