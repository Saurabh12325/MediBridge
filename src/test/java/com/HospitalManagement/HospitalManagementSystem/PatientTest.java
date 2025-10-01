package com.HospitalManagement.HospitalManagementSystem;

import com.HospitalManagement.HospitalManagementSystem.Entity.Patient;
import com.HospitalManagement.HospitalManagementSystem.Repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;
    @Test
    public void testPatientRepository() {
     List<Patient> patientList =  patientRepository.findAll();
     System.out.println(patientList);

    }
}
