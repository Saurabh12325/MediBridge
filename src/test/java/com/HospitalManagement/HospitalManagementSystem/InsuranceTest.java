package com.HospitalManagement.HospitalManagementSystem;

import com.HospitalManagement.HospitalManagementSystem.Entity.Insurance;
import com.HospitalManagement.HospitalManagementSystem.Entity.Patient;
import com.HospitalManagement.HospitalManagementSystem.Service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;
    @Test
    public void testInsurance(){
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030,5,12))
                .build();
       Patient patient =  insuranceService.assignInsurancetoPatient(insurance,1L);
       System.out.println(patient);
    }
}
