package com.cgm.patient_management.rest;

import com.cgm.patient_management.dto.PatientDTO;
import com.cgm.patient_management.model.Patient;
import com.cgm.patient_management.repository.PatientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

// TODO: Add more tests for negative scenarios
@QuarkusTest
class PatientResourceIT {

    @Inject
    ObjectMapper objectMapper;

    @Inject
    PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        patientRepository.deleteAll();
    }


    @Test
    void testCreatingAPatient() throws JsonProcessingException {
        String body = objectMapper.writeValueAsString(getPatientDTO());
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when().post("/patients")
                .then()
                .statusCode(201);
    }

    @Test
    void testGettingAPatient() {
        patientRepository.persist(getPatient());
        given()
                .when().get("/patients/1")
                .then()
                .statusCode(200)
                .body("name", is("John"))
                .body("surname", is("Doe"))
                .body("dateOfBirth", is("2000-01-01"))
                .body("socialSecurityNumber", is("XXX"));
    }

    private PatientDTO getPatientDTO() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setName("John");
        patientDTO.setSurname("Doe");
        patientDTO.setDateOfBirth("2000-01-01");
        patientDTO.setSocialSecurityNumber("XXX");
        return patientDTO;
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