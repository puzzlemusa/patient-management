package com.cgm.patient_management.repository;

import com.cgm.patient_management.model.Visit;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class VisitRepository implements PanacheRepository<Visit> {
}
