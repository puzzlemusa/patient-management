package com.cgm.patient_management.service;

import com.cgm.patient_management.dto.PatientDTO;
import com.cgm.patient_management.dto.VisitDTO;
import com.cgm.patient_management.model.Visit;
import com.cgm.patient_management.model.VisitReason;
import com.cgm.patient_management.model.VisitType;
import com.cgm.patient_management.repository.VisitRepository;
import com.cgm.patient_management.service.mapper.VisitMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class VisitService {

    @Inject
    VisitMapper visitMapper;

    @Inject
    VisitRepository visitRepository;

    @Inject
    PatientService patientService;


    public List<VisitDTO> getAllPatients() {
        return visitRepository.listAll().stream().map(visitMapper::toDTO).toList();
    }

    public VisitDTO getVisit(Long visitId) {
        Optional<Visit> optionalPatient = visitRepository.findByIdOptional(visitId);
        if (optionalPatient.isPresent()) {
            return visitMapper.toDTO(optionalPatient.get());
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    public void save(VisitDTO visitDTO) {
        Optional<PatientDTO> optionalPatientDTO = patientService.getPatient(visitDTO.getPatientId());
        if (optionalPatientDTO.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        } else {
            Visit visit = visitMapper.toEntity(visitDTO);
            visitRepository.persist(visit);
        }
    }

    public Response updateVisit(Long visitId, VisitDTO visitDTO) {
        Optional<Visit> optionalVisit = visitRepository.findByIdOptional(visitId);
        if (optionalVisit.isPresent()) {
            Visit visit = visitMapper.toEntity(visitDTO);
            visit.id = visitId;
            visitRepository.getEntityManager().merge(visit);
            return Response.status(Response.Status.OK).build();
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
