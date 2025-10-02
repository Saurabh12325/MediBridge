package com.HospitalManagement.HospitalManagementSystem.Repository;

import com.HospitalManagement.HospitalManagementSystem.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
}