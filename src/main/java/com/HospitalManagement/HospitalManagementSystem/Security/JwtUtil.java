package com.HospitalManagement.HospitalManagementSystem.Security;


import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtUtil {


//    we need three things to generate a jwt token secretKry , header , Payload
      @Value("${jwt.secret}")
      private String jwtSecretKey;
  private SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
  }

}
