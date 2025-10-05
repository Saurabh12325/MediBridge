//package com.HospitalManagement.HospitalManagementSystem.Repository;
//
//
//import com.HospitalManagement.HospitalManagementSystem.Entity.Type.AuthProviderType;
//import com.HospitalManagement.HospitalManagementSystem.Entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    Optional<User> findByUsername(String username);
//
//    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);
//}