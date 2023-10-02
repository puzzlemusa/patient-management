package com.cgm.patient_management.rest;

import com.cgm.patient_management.dto.VisitDTO;
import com.cgm.patient_management.model.Patient;
import com.cgm.patient_management.model.Visit;
import com.cgm.patient_management.model.VisitReason;
import com.cgm.patient_management.model.VisitType;
import com.cgm.patient_management.repository.PatientRepository;
import com.cgm.patient_management.repository.VisitRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO add more negative scenarios
@QuarkusTest
class VisitResourceIT {

    @Inject
    ObjectMapper objectMapper;

    @Inject
    PatientRepository patientRepository;

    @Inject
    VisitRepository visitRepository;

    @BeforeEach
    void setUp() {
        visitRepository.deleteAll();
        patientRepository.deleteAll();
        patientRepository.persist(getPatient());
    }

    // TODO will fail if run with other tests. Why? When run with others, patientId need to assign dynamically. Currently it is fixed 1.
    @Test
    void testCreatingAVisit() throws JsonProcessingException {
        String body = objectMapper.writeValueAsString(getVisitDTO());
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when().post("/visits")
                .then()
                .statusCode(201);
    }

    // TODO will fail if run with other tests. Why? When run with others, patientId need to assign dynamically. Currently it is fixed 1.
    @Test
    void testGettingAVisit() {
        visitRepository.persist(getVisit());
        given()
                .when().get("/visits/1")
                .then()
                .statusCode(200)
                .body("patientId", is(1))
                .body("visitType", is("AT_HOME"))
                .body("visitReason", is("FIRST_VISIT"))
                .body("familyHistory", is("N/A"));
    }

    @Test
    void testUpdatingAVisit() throws JsonProcessingException {
        visitRepository.persist(getVisit());
        VisitDTO updatedVisitDTO = getVisitDTO();
        updatedVisitDTO.setVisitReason("URGENT");
        String body = objectMapper.writeValueAsString(updatedVisitDTO);
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when().put("/visits/1")
                .then()
                .statusCode(200);
        Visit visit = visitRepository.findById(1L);
        assertEquals(1, visit.getPatientId());
        assertEquals(VisitType.AT_HOME, visit.getVisitType());
        assertEquals(VisitReason.URGENT, visit.getVisitReason());
        assertEquals("N/A", visit.getFamilyHistory());
    }

    private VisitDTO getVisitDTO() {
        VisitDTO visitDTO = new VisitDTO();
        visitDTO.setPatientId(1L);
        visitDTO.setDateTime("2023-10-01T23:26");
        visitDTO.setVisitType("AT_HOME");
        visitDTO.setVisitReason("FIRST_VISIT");
        visitDTO.setFamilyHistory("N/A");
        return visitDTO;
    }

    private Visit getVisit() {
        Visit visit = new Visit();
        visit.setPatientId(1L);
        visit.setDateTime(LocalDateTime.now());
        visit.setVisitType(VisitType.AT_HOME);
        visit.setVisitReason(VisitReason.FIRST_VISIT);
        visit.setFamilyHistory("N/A");
        return visit;
    }

    private Patient getPatient() {
        Patient patient = new Patient();
        patient.setName("John");
        patient.setSurname("Doe");
        patient.setDateOfBirth("2000-01-01");
        patient.setSocialSecurityNumber("XXX");
        return patient;
    }
}
