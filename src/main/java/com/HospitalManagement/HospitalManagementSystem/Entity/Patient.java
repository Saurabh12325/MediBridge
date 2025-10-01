package com.HospitalManagement.HospitalManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@ToString
@Table(name = "patient_tbl")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ToString.Exclude
    private LocalDate birthDate;
    private String gender;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroupType;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


}
