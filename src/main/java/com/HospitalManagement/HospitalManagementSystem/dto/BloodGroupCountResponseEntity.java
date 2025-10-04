package com.HospitalManagement.HospitalManagementSystem.dto;


import com.HospitalManagement.HospitalManagementSystem.Entity.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodGroupCountResponseEntity {

    private BloodGroupType bloodGroupType;
    private Long count;
}