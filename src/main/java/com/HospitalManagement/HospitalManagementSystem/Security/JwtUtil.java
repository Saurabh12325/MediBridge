package com.HospitalManagement.HospitalManagementSystem.Security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {


//    we need three things to generate a jwt token secretKry , header , Payload
      @Value("${jwt.secret}")
      private String jwtSecretKey;


}
