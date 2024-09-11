package com.sn.hms.doctor.serviceImpl;

import com.sn.hms.doctor.entity.Appointment;
import com.sn.hms.doctor.repository.AppointmentRepo;
import com.sn.hms.doctor.service.AppointmentService;
import com.sn.hms.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Override
    public Appointment createAppointment(Appointment appointment) {
        appointment.setRegisterDate(new Date());
        Date registerDate = appointment.getRegisterDate();
        String appointmentNumber = appointment.getAppointmentNumber();

        List<Appointment> existingAppointments = appointmentRepo.findByRegisterDateAndAppointmentNumber(registerDate, appointmentNumber);

        if (!existingAppointments.isEmpty()) {
            throw new RuntimeException("Appointment number already exists for the given date.");
        }

        return appointmentRepo.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    @Override
    public Appointment updateAppointmentById(Appointment appointment, Long aId) {
        Appointment existingAppointment = appointmentRepo.findById(aId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "appointment id", aId));

        Date registerDate = appointment.getRegisterDate();
        String appointmentNumber = appointment.getAppointmentNumber();

        List<Appointment> existingAppointments = appointmentRepo.findByRegisterDateAndAppointmentNumber(registerDate, appointmentNumber);
        if (!existingAppointments.isEmpty() && !existingAppointments.get(0).getId().equals(aId)) {
            throw new RuntimeException("Appointment number already exists for the given date.");
        }

        existingAppointment.setUpdatedDate(new Date());
        existingAppointment.setName(appointment.getName());
        existingAppointment.setDoctorName(appointment.getDoctorName());
        existingAppointment.setSymptoms(appointment.getSymptoms());
        existingAppointment.setAge(appointment.getAge());
        existingAppointment.setMobile(appointment.getMobile());
        existingAppointment.setAppointmentNumber(appointment.getAppointmentNumber());

        return appointmentRepo.save(existingAppointment);
    }

    @Override
    public Optional<Appointment> getAppointmentById(Long aId) {
        return Optional.ofNullable(appointmentRepo.findById(aId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "appointment id", aId)));
    }

    @Override
    public void deleteAppointmentById(Long aId) {
        if (!appointmentRepo.existsById(aId)) {
            throw new ResourceNotFoundException("Appointment", "appointment id", aId);
        }
        appointmentRepo.deleteById(aId);
    }
}
