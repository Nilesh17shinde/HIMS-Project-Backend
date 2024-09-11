package com.sn.hms.doctor.service;

import com.sn.hms.doctor.entity.Medicine;
import com.sn.hms.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface MedicineService {
    Medicine createMedicine(Medicine medicine);
    List<Medicine> getAllMedicine();
    Medicine updateMedicineById(Medicine updatedMedicine, Long mId);
    Optional<Medicine> getMedicineById(Long mId) throws ResourceNotFoundException;
    void deleteMedicineById(Long mId);
    Optional<Medicine> findByDrugsName(String drugsName);
}
