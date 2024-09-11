package com.sn.hms.doctor.repository;

import com.sn.hms.doctor.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    List<Appointment> findByRegisterDateAndAppointmentNumber(Date registerDate, String appointmentNumber);
}
