package com.sn.hms.repository;

//import com.sn.hms.doctor.entity.Patient;
import com.sn.hms.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Long> {
}
