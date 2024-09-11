package com.sn.hms.controller;

//import com.sn.hms.doctor.entity.Patient;
import com.sn.hms.entity.Patient;
import com.sn.hms.exception.ResourceNotFoundException;
import com.sn.hms.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.*;
@RestController
@RequestMapping("/api/patient")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Patient Controller", description = "This is patient API for Patient Operation")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/insert")
    @Operation(summary = "Add new Patients", description = "This is Patient API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New Patient Created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        Patient savedPatient = patientService.createPatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatientById(@Valid @RequestBody Patient patient, @PathVariable Long id) {
        Patient updatedPatient = patientService.updatePatientById(patient, id);
        return ResponseEntity.ok(updatedPatient);
    }

    @GetMapping("/get/{pid}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long pid) {
        Patient patient = patientService.getPatientById(pid)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", pid));
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete Patient", description = "Delete a patient by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient Deleted Successfully"),
            @ApiResponse(responseCode = "404", description = "Patient Not Found")
    })
    public ResponseEntity<Map<String, Boolean>> deletePatientById(@PathVariable Long id) {
        patientService.deletePatientById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Successfully!", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}


//    public ResponseEntity<?> deletePatientById(@PathVariable Long id) {
//        try {
//           Patient delete= patientService.deletePatientById(id);
//            System.out.println(delete+"Patient deleted successfully.");
//            return ResponseEntity.ok().build(); // Return 200 OK if deletion is successful
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting patient"); // Return 500 Internal Server Error if an error occurs
//        }
//    }

