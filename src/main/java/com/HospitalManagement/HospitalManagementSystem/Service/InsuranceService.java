package com.HospitalManagement.HospitalManagementSystem.Service;

import com.HospitalManagement.HospitalManagementSystem.Entity.Insurance;
import com.HospitalManagement.HospitalManagementSystem.Entity.Patient;
import com.HospitalManagement.HospitalManagementSystem.Repository.InsuranceRepository;
import com.HospitalManagement.HospitalManagementSystem.Repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;
    @Transactional
    public Patient assignInsurancetoPatient(Insurance insurance, Long Patientid){
        Patient patient = patientRepository.findById(Patientid).orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        return patient;
    }
}
