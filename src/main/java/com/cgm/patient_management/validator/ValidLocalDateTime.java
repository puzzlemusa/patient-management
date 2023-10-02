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
@Constraint(validatedBy = LocalDateTimeValidator.class)
public @interface ValidLocalDateTime {

    String message() default "Invalid visit time. Date format should be: yyyy-MM-ddTHH:mm";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
