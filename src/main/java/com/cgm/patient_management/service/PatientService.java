package com.cgm.patient_management.service;

import com.cgm.patient_management.dto.PatientDTO;
import com.cgm.patient_management.model.Patient;
import com.cgm.patient_management.repository.PatientRepository;
import com.cgm.patient_management.service.mapper.PatientMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class PatientService {

    @Inject
    PatientMapper patientMapper;

    @Inject
    PatientRepository patientRepository;


    public List<PatientDTO> getAllPatients() {
        return patientRepository.listAll().stream().map(patientMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<PatientDTO> getPatient(Long patientId) {
        Optional<Patient> optionalPatient = patientRepository.findByIdOptional(patientId);
        return optionalPatient.map(patient -> patientMapper.toDTO(patient));
    }

    public void save(PatientDTO patientDTO) {
        Patient patient = patientMapper.toEntity(patientDTO);
        patientRepository.persist(patient);
    }


}
