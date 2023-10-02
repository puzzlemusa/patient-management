package com.cgm.patient_management.service.mapper;

import com.cgm.patient_management.dto.PatientDTO;
import com.cgm.patient_management.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface PatientMapper {

    Patient toEntity(PatientDTO patientDTO);

    PatientDTO toDTO(Patient patient);
}
