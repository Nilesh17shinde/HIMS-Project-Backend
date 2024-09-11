package com.sn.hms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Age cannot be empty")
    private String age;
    @NotEmpty(message = "Mobile cannot be empty")
    private String mobile;
    @NotEmpty(message = "Blood cannot be empty")
    private String blood;
    @NotEmpty(message = "Gender cannot be empty")
    private String Gender;

    @NotEmpty(message = "Prescription cannot be empty")
    private String prescription;

    @NotEmpty(message = "Dose cannot be empty")
    private String dose;

    @NotEmpty(message = "Fees cannot be empty")
    private String fees;

    private String urgency;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}

