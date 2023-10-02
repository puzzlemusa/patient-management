package com.cgm.patient_management.rest;

import com.cgm.patient_management.dto.VisitDTO;
import com.cgm.patient_management.service.VisitService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/visits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VisitResource {

    @Inject
    VisitService visitService;

    @POST
    public Response createVisit(@Valid VisitDTO visitDTO) {
        visitService.save(visitDTO);
        //TODO return location of created resource
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<VisitDTO> getAllVisits() {
        return visitService.getAllPatients();
    }

    @GET
    @Path("/{visitId}")
    public VisitDTO getVisit(@PathParam("visitId") Long visitId) {
        return visitService.getVisit(visitId);
    }

    @PUT
    @Path("/{visitId}")
    @Transactional
    public Response updateVisit(@PathParam("visitId") Long visitId, @Valid VisitDTO visitDTO) {
        return visitService.updateVisit(visitId, visitDTO);
    }
}
