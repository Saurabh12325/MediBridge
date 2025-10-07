package com.HospitalManagement.HospitalManagementSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnBoardDoctorRequestDto {
    private Long userId;
    private String specialization;
    private String name;
}