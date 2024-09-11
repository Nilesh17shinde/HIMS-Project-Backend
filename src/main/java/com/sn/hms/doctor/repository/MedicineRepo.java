package com.sn.hms.doctor.repository;

import com.sn.hms.doctor.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByMedicineId(String medicineId);
    Optional<Medicine> findByDrugsName(String drugsName);
}
