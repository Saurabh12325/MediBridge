package com.HospitalManagement.HospitalManagementSystem;

import com.HospitalManagement.HospitalManagementSystem.Entity.Appointment;
import com.HospitalManagement.HospitalManagementSystem.Entity.Insurance;
import com.HospitalManagement.HospitalManagementSystem.Entity.Patient;
import com.HospitalManagement.HospitalManagementSystem.Service.AppointmentService;
import com.HospitalManagement.HospitalManagementSystem.Service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;
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

    @Test
    public  void testCrerateAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,10,12,12,0))
                .reason("Cardiac")
                .build();
      var appointment1 =   appointmentService.createNewAppointment(appointment,1L,1L);
      System.out.println(appointment1);
    }
}
