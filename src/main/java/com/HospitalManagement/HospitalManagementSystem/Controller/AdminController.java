package com.HospitalManagement.HospitalManagementSystem.Controller;

import com.HospitalManagement.HospitalManagementSystem.Service.DoctorService;
import com.HospitalManagement.HospitalManagementSystem.Service.PatientService;
import com.HospitalManagement.HospitalManagementSystem.dto.DoctorResponseDto;
import com.HospitalManagement.HospitalManagementSystem.dto.OnBoardDoctorRequestDto;
import com.HospitalManagement.HospitalManagementSystem.dto.PatientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final DoctorService doctorService;

    @PostMapping("/onBoardNewDoctor")
    public ResponseEntity<DoctorResponseDto> onBoardNewDoctor(@RequestBody OnBoardDoctorRequestDto onboardDoctorRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.onBoardNewDoctor(onboardDoctorRequestDto));
    }
}