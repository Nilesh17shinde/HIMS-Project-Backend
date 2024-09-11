package com.sn.hms.doctor.service;

import com.sn.hms.doctor.entity.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment updateAppointmentById(Appointment appointment, Long aId);
    Optional<Appointment> getAppointmentById(Long aId);
    void deleteAppointmentById(Long aId);
}
