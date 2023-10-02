package com.cgm.patient_management.service.mapper;

import com.cgm.patient_management.dto.VisitDTO;
import com.cgm.patient_management.model.Visit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface VisitMapper {

    Visit toEntity(VisitDTO visitDTO);

    VisitDTO toDTO(Visit visit);
}
