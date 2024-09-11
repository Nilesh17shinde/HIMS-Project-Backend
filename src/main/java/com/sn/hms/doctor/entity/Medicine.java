package com.sn.hms.doctor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "medicines")
@Data
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Drug name cannot be empty")
    @Column(name = "drugs_name", nullable = false)
    private String drugsName;

    @NotEmpty(message = "Stock cannot be empty")
    @Column(name = "stock", nullable = false)
    private String stock;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "register_date", nullable = false)
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "medicine_id", unique = true, nullable = false)
    private String medicineId;
}
