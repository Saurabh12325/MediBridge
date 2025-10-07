package com.HospitalManagement.HospitalManagementSystem.Security;


import com.HospitalManagement.HospitalManagementSystem.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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


//payload
  public String generateAccessToken(User user){
      return Jwts.builder()
              .setSubject(user.getUsername())
              .claim("userId",user.getId().toString())
              .setIssuedAt(new Date())
              .setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
              .signWith(SignatureAlgorithm.HS256,jwtSecretKey)
              .compact();
  }

    public String extractUsername(String token) {

       return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();


    }

    public boolean validateToken(String token){

        try{
            Jwts.parser()
                    .setSigningKey(jwtSecretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }




}
