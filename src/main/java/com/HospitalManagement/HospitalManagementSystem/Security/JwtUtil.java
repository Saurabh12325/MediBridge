package com.HospitalManagement.HospitalManagementSystem.Security;


import com.HospitalManagement.HospitalManagementSystem.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {


//    we need three things to generate a jwt token secretKry , header , Payload
      @Value("${jwt.secret}")
      private String jwtSecretKey;

      //header
  private SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
  }
//payload
  public String generateAccessToken(User user){
      return Jwts.builder()
              .subject(user.getUsername())
              .claim("userId",user.getId().toString())
              .issuedAt(new Date())
              .expiration(new Date(System.currentTimeMillis()+1000*60*10))
              .signWith(getSecretKey())
              .compact();
  }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }


}
