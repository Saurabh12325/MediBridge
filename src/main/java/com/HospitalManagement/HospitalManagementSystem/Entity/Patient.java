package com.HospitalManagement.HospitalManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "patient_tbl")
 public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 40)
    private String name;


    private LocalDate birthDate;
    private String gender;
    @Column(unique = true,nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroupType;
    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdAt;

@ToString.Exclude
   @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
   @JoinColumn(name = "Patient_Insurance_id") //this is owning side
    private Insurance insurance;

   @OneToMany(mappedBy = "patient")
   @ToString.Exclude
   private List<Appointment> appointments;

}
