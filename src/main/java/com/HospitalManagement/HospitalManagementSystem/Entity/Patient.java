package com.HospitalManagement.HospitalManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "patient_tbl")
 public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @MapsId
    private User user;
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

   @OneToOne(cascade = {CascadeType.ALL} ,orphanRemoval = true)
   @JoinColumn(name = "Patient_Insurance_id") //this is owning side
    private Insurance insurance;

   @OneToMany(mappedBy = "patient")
   private List<Appointment> appointments = new ArrayList<>();




}
