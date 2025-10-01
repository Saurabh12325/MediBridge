package com.HospitalManagement.HospitalManagementSystem.Repository;

import com.HospitalManagement.HospitalManagementSystem.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository<Patient, Long> {


}
