package com.sn.hms.doctor.controller;

import com.sn.hms.doctor.entity.Appointment;
import com.sn.hms.doctor.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointment")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Appointment", description = "To perform CRUD operations on Appointment API")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Operation(summary = "To create an appointment",
            description = "It is used to save an appointment object in the database")

    @PostMapping("/insert")
    public ResponseEntity<Appointment> createAppointment(@Valid @RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentService.createAppointment(appointment);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    @Operation(summary = "To get all appointments",
            description = "It is used to get all appointments from the database")
    @GetMapping("/getAll")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> allAppointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(allAppointments);
    }

    @Operation(summary = "To get an appointment by id",
            description = "It is used to get an appointment from the database by id")
    @GetMapping("/get/{aId}")
    public ResponseEntity<Optional<Appointment>> getAppointmentById(@PathVariable Long aId) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(aId);
        return ResponseEntity.ok(appointment);
    }
    @Operation(summary = "To update an appointment",
            description = "It is used to update an appointment object in the database")
    @PutMapping("/update/{aId}")
    public ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody Appointment appointment, @PathVariable Long aId) {
        Appointment updatedAppointment = appointmentService.updateAppointmentById(appointment, aId);
        return ResponseEntity.ok(updatedAppointment);
    }

    @Operation(summary = "To delete an appointment",
            description = "It is used to delete an appointment object from the database")

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointmentById(id);
        return ResponseEntity.noContent().build();
    }

}
