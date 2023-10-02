package com.cgm.patient_management.rest;

import com.cgm.patient_management.dto.PatientDTO;
import com.cgm.patient_management.service.PatientService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {

    @Inject
    PatientService patientService;

    @POST
    public Response createPatient(@Valid PatientDTO patientDTO) {
        patientService.save(patientDTO);
        //TODO return location of created resource
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GET
    @Path("/{patientId}")
    public PatientDTO getAPatient(@PathParam("patientId") Long patientId) {
        return patientService.getPatient(patientId)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
