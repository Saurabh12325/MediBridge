package com.HospitalManagement.HospitalManagementSystem.Repository;

import com.HospitalManagement.HospitalManagementSystem.Entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}