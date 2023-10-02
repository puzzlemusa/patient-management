package com.cgm.patient_management.repository;

import com.cgm.patient_management.model.Patient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class PatientRepository implements PanacheRepository<Patient> {
}
