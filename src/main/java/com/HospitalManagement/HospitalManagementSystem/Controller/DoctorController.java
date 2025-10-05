//package com.HospitalManagement.HospitalManagementSystem.Controller;
//
//import com.HospitalManagement.HospitalManagementSystem.Entity.User;
//import com.HospitalManagement.HospitalManagementSystem.Service.AppointmentService;
//import com.HospitalManagement.HospitalManagementSystem.dto.AppointmentResponseDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/doctors")
//@RequiredArgsConstructor
//public class DoctorController {
//
//    private final AppointmentService appointmentService;
//
//    @GetMapping("/appointments")
//    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfDoctor() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor(user.getId()));
//    }
//
//}