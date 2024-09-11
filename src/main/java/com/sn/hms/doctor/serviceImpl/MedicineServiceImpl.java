package com.sn.hms.doctor.serviceImpl;

import com.sn.hms.doctor.entity.Medicine;
import com.sn.hms.doctor.repository.MedicineRepo;
import com.sn.hms.doctor.service.MedicineService;
import com.sn.hms.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepo medicineRepo;

    @Override
    public Medicine createMedicine(Medicine medicine) {
        String medicineId = generateUniqueMedicineId(medicine.getDrugsName());
        medicine.setMedicineId(medicineId);
        medicine.setRegisterDate(new Date());
        medicine.setUpdatedDate(null);

        return medicineRepo.save(medicine);
    }

    private String generateUniqueMedicineId(String drugsName) {
        String baseId = drugsName.toUpperCase().replaceAll("\\s+", "_");
        String uniqueId = baseId;

        int counter = 1;
        while (medicineRepo.findByMedicineId(uniqueId).isPresent()) {
            uniqueId = baseId + "_" + counter++;
        }

        return uniqueId;
    }

    @Override
    public List<Medicine> getAllMedicine() {
        return medicineRepo.findAll();
    }

    @Override
    public Medicine updateMedicineById(Medicine updatedMedicine, Long mId) {
        Medicine existingMedicine = medicineRepo.findById(mId)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine", "medicine id", mId));

        if (!existingMedicine.getDrugsName().equals(updatedMedicine.getDrugsName())) {
            String newMedicineId = generateUniqueMedicineId(updatedMedicine.getDrugsName());
            existingMedicine.setMedicineId(newMedicineId);
        }

        existingMedicine.setDrugsName(updatedMedicine.getDrugsName());
        existingMedicine.setStock(updatedMedicine.getStock());
        existingMedicine.setUpdatedDate(new Date());

        return medicineRepo.save(existingMedicine);
    }

    @Override
    public Optional<Medicine> getMedicineById(Long mId) {
        Optional<Medicine> medicine = medicineRepo.findById(mId);
        if (medicine.isEmpty()) {
            throw new ResourceNotFoundException("Medicine", "medicine id", mId);
        }
        return medicine;
    }

    @Override
    public void deleteMedicineById(Long mId) {
        if (!medicineRepo.existsById(mId)) {
            throw new ResourceNotFoundException("Medicine", "medicine id", mId);
        }
        medicineRepo.deleteById(mId);
    }

    @Override
    public Optional<Medicine> findByDrugsName(String drugsName) {
        return medicineRepo.findByDrugsName(drugsName);
    }
}
