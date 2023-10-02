package com.cgm.patient_management.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patient extends PanacheEntity {

    private String name;
    private String surname;
    private String dateOfBirth;
    private String socialSecurityNumber;
}
