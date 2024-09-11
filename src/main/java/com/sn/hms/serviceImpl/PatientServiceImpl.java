package com.sn.hms.serviceImpl;

//import com.sn.hms.doctor.entity.Patient;
import com.sn.hms.entity.Patient;
import com.sn.hms.exception.ResourceNotFoundException;
import com.sn.hms.repository.PatientRepo;
import com.sn.hms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    // Create a new patient: Only set registerDate
    @Override
    public Patient createPatient(Patient patient) {
        patient.setRegisterDate(new Date()); // Set the registerDate only at creation time
        return patientRepo.save(patient);
    }

    // Update an existing patient: Only set updatedDate
    @Override
    public Patient updatePatientById(Patient patient, Long pid) {
        // Fetch the existing patient from the repository
        Optional<Patient> existingPatientOptional = patientRepo.findById(pid);

        if (existingPatientOptional.isPresent()) {
            Patient existingPatient = existingPatientOptional.get();

            // Set updatedDate to the current date
            existingPatient.setUpdatedDate(new Date());

            // Update only the necessary fields
            existingPatient.setName(patient.getName());
            existingPatient.setAge(patient.getAge());
            existingPatient.setMobile(patient.getMobile());
            existingPatient.setBlood(patient.getBlood());
            existingPatient.setGender(patient.getGender());
            existingPatient.setPrescription(patient.getPrescription());
            existingPatient.setDose(patient.getDose());
            existingPatient.setFees(patient.getFees());
            existingPatient.setUrgency(patient.getUrgency());

            // Save the updated patient
            return patientRepo.save(existingPatient);
        } else {
            // Handle case where patient with given ID is not found
            throw new ResourceNotFoundException("Patient", "id", pid);
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(Long pid) {
        return patientRepo.findById(pid);
    }

    @Override
    public void deletePatientById(Long pid) {
        Optional<Patient> patient = patientRepo.findById(pid);

        if (patient.isPresent()) {
            patientRepo.deleteById(pid);
        } else {
            throw new ResourceNotFoundException("Patient", "id", pid);
        }
    }
}
