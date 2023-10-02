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
@Constraint(validatedBy = LocalDateValidator.class)
public @interface ValidLocalDate {

    String message() default "Invalid date of birth. Date format should be: yyyy-MM-dd";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
