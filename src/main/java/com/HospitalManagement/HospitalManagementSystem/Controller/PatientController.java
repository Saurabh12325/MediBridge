package com.HospitalManagement.HospitalManagementSystem.Controller;

import com.HospitalManagement.HospitalManagementSystem.Entity.User;
import com.HospitalManagement.HospitalManagementSystem.Service.AppointmentService;
import com.HospitalManagement.HospitalManagementSystem.Service.PatientService;
import com.HospitalManagement.HospitalManagementSystem.dto.AppointmentResponseDto;
import com.HospitalManagement.HospitalManagementSystem.dto.CreateAppointmentRequestDto;
import com.HospitalManagement.HospitalManagementSystem.dto.PatientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
  private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }

    @GetMapping("/profile")
    private ResponseEntity<PatientResponseDto> getPatientProfile() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long patientId = user.getId();
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

}