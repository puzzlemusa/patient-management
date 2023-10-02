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
@Constraint(validatedBy = VisitReasonValidator.class)
public @interface ValidVisitReason {

    String message() default "Invalid visit reason. Valid visit reasons are: FIRST_VISIT, RECURRING_VISIT, URGENT";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
