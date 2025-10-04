package com.HospitalManagement.HospitalManagementSystem.Service;

import com.HospitalManagement.HospitalManagementSystem.Entity.Appointment;
import com.HospitalManagement.HospitalManagementSystem.Entity.Doctor;
import com.HospitalManagement.HospitalManagementSystem.Entity.Patient;
import com.HospitalManagement.HospitalManagementSystem.Repository.AppointmentRepository;
import com.HospitalManagement.HospitalManagementSystem.Repository.DoctorRepository;
import com.HospitalManagement.HospitalManagementSystem.Repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.IllegalFormatWidthException;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public  Appointment createNewAppointment(Appointment appointment , Long doctorId, Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));
        if(appointment.getId()!=null){
            throw new IllegalArgumentException("Appoointment should not have ");
        }
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.getAppointments().add(appointment); 
        appointmentRepository.save(appointment);

    }


}
