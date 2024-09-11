package com.sn.hms.doctor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "appointments")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Doctor Name cannot be empty")
    private String doctorName;

    @NotEmpty(message = "Age cannot be empty")
    private String age;

    @NotEmpty(message = "Symptoms cannot be empty")
    private String symptoms;

    @NotEmpty(message = "Mobile cannot be empty")
    private String mobile;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "appointment_number")
    private String appointmentNumber;
}
