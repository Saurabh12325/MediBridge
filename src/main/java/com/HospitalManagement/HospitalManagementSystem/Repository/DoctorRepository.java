package com.HospitalManagement.HospitalManagementSystem.Repository;

import com.HospitalManagement.HospitalManagementSystem.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}