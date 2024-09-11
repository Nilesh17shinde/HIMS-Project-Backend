package com.sn.hms.service;

//import com.sn.hms.doctor.entity.Patient;
import com.sn.hms.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient createPatient(Patient patient);
    Patient updatePatientById(Patient patient, Long pid);
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(Long pid);
    void deletePatientById(Long pid);
}
