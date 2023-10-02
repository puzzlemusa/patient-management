package com.cgm.patient_management.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Visit extends PanacheEntity {

    private Long patientId;
    private LocalDateTime dateTime;
    private VisitType visitType;
    private VisitReason visitReason;
    private String familyHistory;
}
