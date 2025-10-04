package com.HospitalManagement.HospitalManagementSystem.dto;




import com.HospitalManagement.HospitalManagementSystem.Entity.BloodGroupType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientResponseDto {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroupType bloodGroup;
}