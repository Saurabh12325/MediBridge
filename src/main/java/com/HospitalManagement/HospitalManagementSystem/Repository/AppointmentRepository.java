package com.HospitalManagement.HospitalManagementSystem.Repository;

import com.HospitalManagement.HospitalManagementSystem.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}