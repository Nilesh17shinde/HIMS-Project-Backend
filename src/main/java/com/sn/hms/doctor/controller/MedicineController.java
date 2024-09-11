package com.sn.hms.doctor.controller;

import com.sn.hms.doctor.entity.Medicine;
import com.sn.hms.doctor.service.MedicineService;
import com.sn.hms.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicine")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping("/insert")
    public ResponseEntity<Object> insertMedicine(@Valid @RequestBody Medicine medicine) {
        try {
            Medicine savedMedicine = medicineService.createMedicine(medicine);
            return ResponseEntity.status(HttpStatus.CREATED).body("Medicine with ID " + savedMedicine.getId() + " and Drugs ID " + savedMedicine.getMedicineId() + " added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add medicine: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getMedicineById(@PathVariable Long id) {
        try {
            Optional<Medicine> medicine = medicineService.getMedicineById(id);
            if (medicine.isPresent()) {
                return ResponseEntity.ok(medicine.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicine with ID " + id + " not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve medicine: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllMedicine() {
        try {
            List<Medicine> allMedicines = medicineService.getAllMedicine();
            return ResponseEntity.ok(allMedicines);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve all medicines: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateMedicine(@Valid @RequestBody Medicine medicine, @PathVariable Long id) {
        try {
            Medicine updatedMedicine = medicineService.updateMedicineById(medicine, id);
            return ResponseEntity.ok("Medicine with ID " + id + " updated successfully.");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicine with ID " + id + " not found for update.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update medicine: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteMedicine(@PathVariable Long id) {
        try {
            medicineService.deleteMedicineById(id);
            return ResponseEntity.ok("Medicine with ID " + id + " is deleted Successfully.");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicine with ID " + id + " not found for deletion.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete medicine: " + e.getMessage());
        }
    }

    @GetMapping("/findByDrugsName/{drugsName}")
    public ResponseEntity<Object> findByDrugsName(@PathVariable String drugsName) {
        try {
            Optional<Medicine> medicines = medicineService.findByDrugsName(drugsName);
            if (!medicines.isEmpty()) {
                return ResponseEntity.ok(medicines);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No medicines found with drugs name '" + drugsName + "'.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to find medicine by drugs name: " + e.getMessage());
        }
    }
}
