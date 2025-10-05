package com.HospitalManagement.HospitalManagementSystem.Service;


import com.HospitalManagement.HospitalManagementSystem.Entity.Doctor;
import com.HospitalManagement.HospitalManagementSystem.Entity.Type.RoleType;
//import com.HospitalManagement.HospitalManagementSystem.Entity.User;
import com.HospitalManagement.HospitalManagementSystem.Repository.DoctorRepository;
//import com.HospitalManagement.HospitalManagementSystem.Repository.UserRepository;
import com.HospitalManagement.HospitalManagementSystem.dto.DoctorResponseDto;
import com.HospitalManagement.HospitalManagementSystem.dto.OnBoardDoctorRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
//    private final UserRepository userRepository;

    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .collect(Collectors.toList());
    }


    @Transactional
    public DoctorResponseDto onBoardNewDoctor(OnBoardDoctorRequestDto onBoardDoctorRequestDto) {
//        User user = userRepository.findById(onBoardDoctorRequestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(doctorRepository.existsById(onBoardDoctorRequestDto.getUserId())) {
            throw new IllegalArgumentException("Already a doctor");
        }

        Doctor doctor = Doctor.builder()
                .name(onBoardDoctorRequestDto.getName())
                .Specialization(onBoardDoctorRequestDto.getSpecialization())
//                .user(user)
                .build();

//        user.getRoles().add(RoleType.DOCTOR);

        return modelMapper.map(doctorRepository.save(doctor), DoctorResponseDto.class);
    }
}
